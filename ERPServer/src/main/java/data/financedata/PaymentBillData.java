package main.java.data.financedata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.FullException;
import main.java.dataservice.financedataservice.PaymentBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.PaymentBillPO;
import main.java.po.bill.financebill.TransItemPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author 陈思彤
 * @description
 * @date 2017/12/10
 */
public class PaymentBillData implements PaymentBillDataService {
    /**
     * @param query [单据筛选条件]
     * @return 符合筛选条件的付款单
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<PaymentBillPO> finds(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<PaymentBillPO> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ArrayList<String> sqlOfQuery = new ArrayList<>();
            String sql;
            ResultSet resultSet;
            if ("审批不通过".equals(query.state) || "草稿".equals(query.state)) {
                sql = "SELECT * FROM PaymentBill WHERE operatorID='" + query.operator + "' AND state='" + query.state + "'";
                sqlOfQuery.add(sql);
            } else {
                sql = "SELECT * FROM User WHERE name='" + query.operator + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String operatorID = resultSet.getString("ID");
                    sql = "SELECT * FROM PaymentBill WHERE (operatorID='" + operatorID + "'" + (query.start == null ? "" : " OR (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "')") + ") AND state='" + query.state + "'";
                    sqlOfQuery.add(sql);
                }
                sql = "SELECT * FROM Client WHERE name='" + query.client + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String clientID = resultSet.getString("ID");
                    sql = "SELECT * FROM PaymentBill WHERE (clientID='" + clientID + "'" + (query.start == null ? "" : " OR (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "')") + ") AND state='" + query.state + "'";
                    sqlOfQuery.add(sql);
                }
            }
            for (int i = 0; i < sqlOfQuery.size(); i++) {
                sql = sqlOfQuery.get(i);
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    sql = "SELECT * FROM TransItem WHERE site_ID='" + ID + "'";
                    ResultSet temp = statement.executeQuery(sql);
                    ArrayList<TransItemPO> itemPOS = new ArrayList<>();
                    while (temp.next()) {
                        itemPOS.add(new TransItemPO(temp.getString("accountID"), temp.getDouble("transAmount"), temp.getString("comment")));
                    }
                    PaymentBillPO paymentBillPO = new PaymentBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), resultSet.getDouble("total"), resultSet.getString("clientID"), itemPOS);
                    paymentBillPO.setID(ID);
                    list.add(paymentBillPO);
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
     * @param po [付款单]
     * @return 新建付款单的ID
     * @throws RemoteException,DataException,FullException
     */
    @Override
    public synchronized String insert(PaymentBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT PaymentBill FROM DataHelper";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int before = resultSet.getInt(1);
            sql = "SELECT COUNT(keyID) FROM PaymentBill";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            int all = resultSet.getInt(1);
            if (all - before >= 99999)
                throw new FullException();
            sql = "INSERT INTO PaymentBill (state, time, operatorID, comment, total, clientID) VALUES ('" + po.getState() + "', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getClientID() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "FKD-" + new SimpleDateFormat("yyyyMMdd-").format(po.getTime()) + String.format("%0" + 5 + "d", key - before);
            sql = "UPDATE PaymentBill SET ID='" + ID + "' WHERE keyID=" + key;
            statement.executeUpdate(sql);
            ArrayList<TransItemPO> list = po.getTransList();
            for (int i = 0; i < list.size(); i++) {
                sql = "INSERT INTO TransItem VALUES ('" + ID + "', '" + list.get(i).accountID + "', '" + list.get(i).transAmount + "', '" + list.get(i).comment + "')";
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
     * @param po [更新后的付款单]
     * @throws RemoteException,DataException
     */
    @Override
    public synchronized void update(PaymentBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String ID = po.getID();
            String sql = "UPDATE PaymentBill SET state='" + po.getState() + "', comment='" + po.getComment() + "', total='" + po.getTotal() + "', clientID='" + po.getClientID() + "' WHERE ID='" + ID + "'";
            statement.executeUpdate(sql);
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
