package main.java.data.financedata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.dataservice.financedataservice.ReceiptBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.ReceiptBillPO;
import main.java.po.bill.financebill.TransItemPO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * @author 陈思彤
 * @description
 * @date 2017/12/10
 */
public class ReceiptBillData extends UnicastRemoteObject implements ReceiptBillDataService {
    /**
     * rmi
     **/
    public ReceiptBillData() throws RemoteException {
        ReceiptBillDataService receiptBillDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/ReceiptBillDataService", receiptBillDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param query [单据筛选条件]
     * @return 符合筛选条件的收款单
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<ReceiptBillPO> finds(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            ArrayList<String> sqlOfQuery = new ArrayList<>();
            ResultSet resultSet;

            /**构造筛选条件**/
            {
                String sql;
                if ("审批不通过".equals(query.state) || "草稿".equals(query.state)) {
                    sql = "SELECT * FROM receiptbill WHERE operatorID='" + query.operator + "' AND state='" + query.state + "'";
                    sqlOfQuery.add(sql);
                } else if ("待审批".equals(query.state)) {
                    sql = "SELECT * FROM receiptbill WHERE state='待审批'";
                    sqlOfQuery.add(sql);
                } else {
                    if (query.start == null && query.operator == null && query.client == null) {
                        sql = "SELECT * FROM receiptbill WHERE state='审批通过'";
                        sqlOfQuery.add(sql);
                    } else if (query.start != null) {
                        sql = "SELECT * FROM receiptbill WHERE (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "') AND state='审批通过'";
                        sqlOfQuery.add(sql);
                    } else {
                        sql = "SELECT * FROM User WHERE name='" + query.operator + "'";
                        resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            String operatorID = resultSet.getString("ID");
                            sql = "SELECT * FROM receiptbill WHERE operatorID='" + operatorID + "' AND " + "state = '审批通过'";
                            sqlOfQuery.add(sql);
                        }
                        sql = "SELECT * FROM Client WHERE name='" + query.client + "'";
                        resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            String clientID = resultSet.getString("ID");
                            sql = "SELECT * FROM receiptbill WHERE clientID='" + clientID + "' AND state='审批通过'";
                            sqlOfQuery.add(sql);
                        }
                    }
                }
            }

            ArrayList<ReceiptBillPO> list = new ArrayList<>();

            /**构造ArrayList<PaymentBillPO>**/
            for (String sql : sqlOfQuery) {
                resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    String tempSql = "SELECT * FROM TransItem WHERE site_ID='" + ID + "'";
                    Statement tempStatement = connection.createStatement();
                    ResultSet tempResultSet = tempStatement.executeQuery(tempSql);

                    /**构造ArrayList<TransItemPO>**/
                    ArrayList<TransItemPO> itemPOS = new ArrayList<>();
                    while (tempResultSet.next()) {
                        itemPOS.add(new TransItemPO(tempResultSet.getString("accountID"), tempResultSet.getDouble("transAmount"), tempResultSet.getString("comment")));
                    }

                    ReceiptBillPO receiptBillPO = new ReceiptBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), resultSet.getDouble("total"), resultSet.getString("clientID"), itemPOS);
                    receiptBillPO.setID(ID);
                    list.add(receiptBillPO);
                }
            }

            statement.close();

            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    /**
     * @param po [收款单]
     * @return 新建收款单的ID
     * @throws RemoteException,DataException,FullException
     */
    @Override
    public synchronized String insert(ReceiptBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            /**获取截止到昨天为止的单据数量**/
            String sql = "SELECT ReceiptBill FROM DataHelper";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int before = resultSet.getInt(1);

            /**获取所有单据数量**/
            sql = "SELECT COUNT(keyID) FROM ReceiptBill";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            int all = resultSet.getInt(1);

            /**检查是否达到今日最大单据数**/
            if (all - before >= 99999)
                throw new FullException();

            /**插入单据**/
            sql = "INSERT INTO ReceiptBill (state, time, operatorID, comment, total, clientID) VALUES ('" + po.getState() + "', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getClientID() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            /**构造ID**/
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "SKD-" + new SimpleDateFormat("yyyyMMdd-").format(po.getTime()) + String.format("%0" + 5 + "d", key - before);

            /**更新ID**/
            sql = "UPDATE ReceiptBill SET ID='" + ID + "' WHERE keyID=" + key;
            statement.executeUpdate(sql);

            /**插入转账列表**/
            ArrayList<TransItemPO> list = po.getTransList();
            for (TransItemPO transItemPO : list) {
                sql = "INSERT INTO TransItem VALUES ('" + ID + "', '" + transItemPO.accountID + "', '" + transItemPO.transAmount + "', '" + transItemPO.comment + "')";
                statement.executeUpdate(sql);
            }

            resultSet.close();
            statement.close();
            return ID;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    /**
     * @param po [更新后的收款单]
     * @throws RemoteException,DataException
     */
    @Override
    public synchronized void update(ReceiptBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            /**更新单据**/
            String ID = po.getID();
            String sql = "UPDATE ReceiptBill SET state='" + po.getState() + "', comment='" + po.getComment() + "', total='" + po.getTotal() + "', clientID='" + po.getClientID() + "' WHERE ID='" + ID + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            /**更新转账列表**/
            sql = "DELETE FROM TransItem WHERE site_ID='" + ID + "'";
            statement.executeUpdate(sql);
            ArrayList<TransItemPO> list = po.getTransList();
            for (int i = 0; i < list.size(); i++) {
                sql = "INSERT INTO TransItem VALUES ('" + ID + "', '" + list.get(i).accountID + "', '" + list.get(i).transAmount + "', '" + list.get(i).comment + "')";
                statement.executeUpdate(sql);
            }

            statement.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }
}
