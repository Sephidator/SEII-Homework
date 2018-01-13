package main.java.data.financedata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.CashBillPO;
import main.java.po.bill.financebill.CashItemPO;

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
 * @date 2017/12/07
 */
public class CashBillData extends UnicastRemoteObject implements CashBillDataService {
    /**
     * rmi
     **/
    public CashBillData() throws RemoteException {
        CashBillDataService cashBillDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/CashBillDataService", cashBillDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param query [单据筛选条件]
     * @return 符合筛选条件的现金费用单
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<CashBillPO> finds(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            ArrayList<String> sqlOfQuery = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet;

            /**构造筛选条件**/
            {
                String sql;
                if ("审批不通过".equals(query.state) || "草稿".equals(query.state)) {
                    sql = "SELECT * FROM cashbill WHERE operatorID='" + query.operator + "' AND state='" + query.state + "'";
                    sqlOfQuery.add(sql);
                } else if ("待审批".equals(query.state)) {
                    sql = "SELECT * FROM cashbill WHERE state='待审批'";
                    sqlOfQuery.add(sql);
                } else {
                    if (query.start == null && query.operator == null && query.client == null) {
                        sql = "SELECT * FROM cashbill WHERE state='审批通过'";
                        sqlOfQuery.add(sql);
                    } else if (query.start != null) {
                        sql = "SELECT * FROM cashbill WHERE (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "') AND state='审批通过'";
                        sqlOfQuery.add(sql);
                    } else {
                        sql = "SELECT * FROM User WHERE name='" + query.operator + "'";
                        resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            String operatorID = resultSet.getString("ID");
                            sql = "SELECT * FROM cashbill WHERE operatorID='" + operatorID + "' AND " + "state = '审批通过'";
                            sqlOfQuery.add(sql);
                        }
                    }
                }
            }

            /**构造ArrayList<CashBillPO>**/
            ArrayList<CashBillPO> list = new ArrayList<>();

            for (String sql : sqlOfQuery) {
                resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    String tempSql = "SELECT * FROM CashItem WHERE site_ID='" + ID + "'";
                    Statement tempStatement = connection.createStatement();
                    ResultSet tempResultSet = tempStatement.executeQuery(tempSql);

                    /**构造ArrayList<CashItemPO>**/
                    ArrayList<CashItemPO> itemPOS = new ArrayList<>();
                    while (tempResultSet.next()) {
                        itemPOS.add(new CashItemPO(tempResultSet.getString("itemName"), tempResultSet.getDouble("amount"), tempResultSet.getString("comment")));
                    }

                    CashBillPO cashBillPO = new CashBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), resultSet.getDouble("total"), resultSet.getString("accountID"), itemPOS);
                    cashBillPO.setID(ID);
                    list.add(cashBillPO);
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
     * @param po [现金费用单]
     * @return 新建现金费用单的ID
     * @throws RemoteException,DataException,FullException
     */
    @Override
    public synchronized String insert(CashBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            /**获取截止到昨天为止的单据数量**/
            String sql = "SELECT CashBill FROM DataHelper";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int before = resultSet.getInt(1);

            /**获取所有单据数量**/
            sql = "SELECT COUNT(keyID) FROM CashBill";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            int all = resultSet.getInt(1);

            /**检查是否达到今日最大单据数**/
            if (all - before >= 99999)
                throw new FullException();

            /**插入单据**/
            sql = "INSERT INTO CashBill (state, time, operatorID, comment, total, accountID) VALUES ('" + po.getState() + "', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getAccountID() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            /**构造ID**/
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "XJFYD-" + new SimpleDateFormat("yyyyMMdd-").format(po.getTime()) + String.format("%0" + 5 + "d", key - before);

            /**更新ID**/
            sql = "UPDATE CashBill SET ID='" + ID + "' WHERE keyID=" + key;
            statement.executeUpdate(sql);

            /**插入条目**/
            ArrayList<CashItemPO> list = po.getItemList();
            for (CashItemPO cashItemPO : list) {
                sql = "INSERT INTO CashItem VALUES ('" + ID + "', '" + cashItemPO.itemName + "', '" + cashItemPO.amount + "', '" + cashItemPO.comment + "')";
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
     * @param po [更新后的现金费用单]
     * @throws RemoteException,DataException
     */
    @Override
    public synchronized void update(CashBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            /**更新单据**/
            String ID = po.getID();
            String sql = "UPDATE CashBill SET state='" + po.getState() + "', comment='" + po.getComment() + "', total='" + po.getTotal() + "', accountID='" + po.getAccountID() + "' WHERE ID='" + ID + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            /**更新条目**/
            sql = "DELETE FROM CashItem WHERE site_ID='" + ID + "'";
            statement.executeUpdate(sql);
            ArrayList<CashItemPO> list = po.getItemList();
            for (CashItemPO cashItemPO : list) {
                sql = "INSERT INTO CashItem VALUES ('" + ID + "', '" + cashItemPO.itemName + "', '" + cashItemPO.amount + "', '" + cashItemPO.comment + "')";
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
