package main.java.data.financedata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.FullException;
import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.CashBillPO;
import main.java.po.bill.financebill.CashItemPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author CST
 * @description Implements CashBillDataService
 * @date 2017/12/9 下午6:14
 */
public class CashBillData implements CashBillDataService {
    /**
     * @param query
     * @return ArrayList<CashBillPO>
     * @throws RemoteException
     */
    @Override
    public ArrayList<CashBillPO> finds(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<CashBillPO> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            String sql;
            CashBillPO cashBillPO;
            if ("草稿".equals(query.state)) {
                sql = "SELECT * FROM CashBillDraft WHERE operatorID='" + query.operatorID + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    sql = "SELECT * FROM CashItem WHERE site_ID='" + ID + "'";
                    ResultSet temp = statement.executeQuery(sql);
                    ArrayList<CashItemPO> itemPOS = new ArrayList<>();
                    while (temp.next()) {
                        itemPOS.add(new CashItemPO(temp.getString("item"), temp.getDouble("amount"), temp.getString("comment")));
                    }
                    cashBillPO = new CashBillPO("草稿", resultSet.getTimestamp("time"), resultSet.getString("operator"), resultSet.getString("comment"), resultSet.getDouble("total"), resultSet.getString("accountID"), itemPOS);
                    list.add(cashBillPO);
                }
            } else {
                if ("审批不通过".equals(query.state))
                    sql = "SELECT * FROM CashBill WHERE visible=TRUE AND operatorID='" + query.operatorID + "' AND state='审批不通过'";
                else if (query.start != null && query.end != null)
                    sql = "SELECT * FROM CashBill WHERE visible=TRUE AND state='" + query.state + "' AND (time BETWEEN '" + query.start + "' AND '" + query.end + "')";
                else if (query.operatorID != null)
                    sql = "SELECT * FROM CashBill WHERE visible=TRUE AND state='" + query.state + "' AND operatorID='" + query.operatorID + "'";
                else
                    sql = "SELECT * FROM CashBill WHERE visible=TRUE AND state='" + query.state + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    sql = "SELECT * FROM CashItem WHERE site_ID='" + ID + "'";
                    ResultSet temp = statement.executeQuery(sql);
                    ArrayList<CashItemPO> itemPOS = new ArrayList<>();
                    while (temp.next()) {
                        itemPOS.add(new CashItemPO(temp.getString("item"), temp.getDouble("amount"), temp.getString("comment")));
                    }
                    cashBillPO = new CashBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operator"), resultSet.getString("comment"), resultSet.getDouble("total"), resultSet.getString("accountID"), itemPOS);
                    cashBillPO.setID(ID);
                    list.add(cashBillPO);
                }
            }
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
     * @param po
     * @return String
     * @throws RemoteException
     */
    @Override
    public synchronized String insert(CashBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql;
            ResultSet resultSet;
            ArrayList<CashItemPO> list = po.getItemList();
            if ("草稿".equals(po.getState())) {
                sql = "INSERT INTO CashBillDraft (time, operatorID, comment, total, accountID) VALUES ('" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getAccountID() + "')";
                statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                resultSet = statement.getGeneratedKeys();
                resultSet.next();
                int key = resultSet.getInt(1);
                for (int i = 0; i < list.size(); i++) {
                    sql = "INSERT INTO CashItem VALUES ('" + key + "', '" + list.get(i).itemName + "', '" + list.get(i).amount + "', '" + list.get(i).comment + "')";
                    statement.executeUpdate(sql);
                }
                return "Success";
            } else {
                sql = "SELECT cashBill FROM DataHelper";
                resultSet = statement.executeQuery(sql);
                resultSet.next();
                int before = resultSet.getInt(1);
                sql = "SELECT COUNT(keyID) FROM CashBill";
                resultSet = statement.executeQuery(sql);
                resultSet.next();
                int all = resultSet.getInt(1);
                if (all - before >= 99999)
                    throw new FullException();
                sql = "INSERT INTO CashBill (state, time, operatorID, comment, total, accountID) VALUES ('待审批', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getAccountID() + "')";
                statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                resultSet = statement.getGeneratedKeys();
                resultSet.next();
                int key = resultSet.getInt(1);
                String ID = "XJFYD-" + new SimpleDateFormat("yyyyMMdd-").format(new Date()) + (key - before);
                sql = "UPDATE CashBill SET ID='" + ID + "' WHERE keyID=" + key;
                statement.executeUpdate(sql);
                for (int i = 0; i < list.size(); i++) {
                    sql = "INSERT INTO CashItem VALUES ('" + ID + "', '" + list.get(i).itemName + "', '" + list.get(i).amount + "', '" + list.get(i).comment + "')";
                    statement.executeUpdate(sql);
                }
                return ID;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    /**
     * @param po
     * @throws RemoteException
     */
    @Override
    public void update(CashBillPO po) throws RemoteException {

    }
}
