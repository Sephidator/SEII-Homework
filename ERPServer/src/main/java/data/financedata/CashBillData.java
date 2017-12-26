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
        ArrayList<CashBillPO> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ArrayList<String> sqlOfQuery = new ArrayList<>();
            String sql;
            ResultSet resultSet;
            if ("审批不通过".equals(query.state) || "草稿".equals(query.state)) {
                sql = "SELECT * FROM CashBill WHERE operatorID='" + query.operator + "' AND state='" + query.state + "'";
                sqlOfQuery.add(sql);
            } else if (query.start == null && query.operator == null && query.client == null) {
                sql = "SELECT * FROM cashbill WHERE state='" + query.state + "'";
                sqlOfQuery.add(sql);
            } else {
                sql = "SELECT * FROM User WHERE name='" + query.operator + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String operatorID = resultSet.getString("ID");
                    sql = "SELECT * FROM CashBill WHERE (operatorID='" + operatorID + "'" + (query.start == null ? "" : " OR (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "')") + ") AND state='" + query.state + "'";
                    sqlOfQuery.add(sql);
                }
            }
            for (int i = 0; i < sqlOfQuery.size(); i++) {
                sql = sqlOfQuery.get(i);
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    sql = "SELECT * FROM CashItem WHERE site_ID='" + ID + "'";
                    ResultSet temp = connection.createStatement().executeQuery(sql);
                    ArrayList<CashItemPO> itemPOS = new ArrayList<>();
                    while (temp.next()) {
                        itemPOS.add(new CashItemPO(temp.getString("itemName"), temp.getDouble("amount"), temp.getString("comment")));
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
            Statement statement = connection.createStatement();
            String sql = "SELECT CashBill FROM DataHelper";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int before = resultSet.getInt(1);
            sql = "SELECT COUNT(keyID) FROM CashBill";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            int all = resultSet.getInt(1);
            if (all - before >= 99999)
                throw new FullException();
            sql = "INSERT INTO CashBill (state, time, operatorID, comment, total, accountID) VALUES ('" + po.getState() + "', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getAccountID() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "XJFYD-" + new SimpleDateFormat("yyyyMMdd-").format(po.getTime()) + String.format("%0" + 5 + "d", key - before);
            sql = "UPDATE CashBill SET ID='" + ID + "' WHERE keyID=" + key;
            statement.executeUpdate(sql);
            ArrayList<CashItemPO> list = po.getItemList();
            for (int i = 0; i < list.size(); i++) {
                sql = "INSERT INTO CashItem VALUES ('" + ID + "', '" + list.get(i).itemName + "', '" + list.get(i).amount + "', '" + list.get(i).comment + "')";
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
            Statement statement = connection.createStatement();
            String ID = po.getID();
            String sql = "UPDATE CashBill SET state='" + po.getState() + "', comment='" + po.getComment() + "', total='" + po.getTotal() + "', accountID='" + po.getAccountID() + "' WHERE ID='" + ID + "'";
            statement.executeUpdate(sql);
            sql = "DELETE FROM CashItem WHERE site_ID='" + ID + "'";
            statement.executeUpdate(sql);
            ArrayList<CashItemPO> list = po.getItemList();
            for (int i = 0; i < list.size(); i++) {
                sql = "INSERT INTO CashItem VALUES ('" + ID + "', '" + list.get(i).itemName + "', '" + list.get(i).amount + "', '" + list.get(i).comment + "')";
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
