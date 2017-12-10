package main.java.data.financedata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.FullException;
import main.java.data.datautility.NotExistException;
import main.java.dataservice.financedataservice.PaymentBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.PaymentBillPO;
import main.java.po.bill.financebill.TransItemPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author CST
 * @description Implements PaymentBillDataService
 * @date 2017/12/10 下午2:36
 */
public class PaymentBillData implements PaymentBillDataService {
    /**
     * @param query
     * @return ArrayList<PaymentBillPO>
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<PaymentBillPO> finds(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<PaymentBillPO> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            String sql;
            PaymentBillPO paymentBillPO;
            if ("草稿".equals(query.state)) {
                sql = "SELECT * FROM PaymentBillDraft WHERE operatorID='" + query.operatorID + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    sql = "SELECT * FROM TransItem WHERE site_ID='" + ID + "'";
                    ResultSet temp = statement.executeQuery(sql);
                    ArrayList<TransItemPO> itemPOS = new ArrayList<>();
                    while (temp.next()) {
                        itemPOS.add(new TransItemPO(temp.getString("accountID"), temp.getDouble("transAmount"), temp.getString("comment")));
                    }
                    paymentBillPO = new PaymentBillPO("草稿", resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), resultSet.getDouble("total"), resultSet.getString("clientID"), itemPOS);
                    list.add(paymentBillPO);
                }
            } else {
                if ("审批不通过".equals(query.state))
                    sql = "SELECT * FROM PaymentBill WHERE operatorID='" + query.operatorID + "' AND state='审批不通过'";
                else if (query.start != null && query.end != null)
                    sql = "SELECT * FROM PaymentBill WHERE visible=TRUE AND state='" + query.state + "' AND (time BETWEEN '" + query.start + "' AND '" + query.end + "')";
                else if (query.operatorID != null)
                    sql = "SELECT * FROM PaymentBill WHERE visible=TRUE AND state='" + query.state + "' AND operatorID='" + query.operatorID + "'";
                else
                    sql = "SELECT * FROM PaymentBill WHERE visible=TRUE AND state='" + query.state + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    sql = "SELECT * FROM TransItem WHERE site_ID='" + ID + "'";
                    ResultSet temp = statement.executeQuery(sql);
                    ArrayList<TransItemPO> itemPOS = new ArrayList<>();
                    while (temp.next()) {
                        itemPOS.add(new TransItemPO(temp.getString("accountID"), temp.getDouble("transAmount"), temp.getString("comment")));
                    }
                    paymentBillPO = new PaymentBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), resultSet.getDouble("total"), resultSet.getString("clientID"), itemPOS);
                    paymentBillPO.setID(ID);
                    list.add(paymentBillPO);
                }
            }
            resultSet.close();
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
     * @param po
     * @return String
     * @throws RemoteException,DataException,FullException
     */
    @Override
    public synchronized String insert(PaymentBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql;
            ResultSet resultSet;
            ArrayList<TransItemPO> list = po.getTransList();
            String ID = "Success";
            if ("草稿".equals(po.getState())) {
                sql = "INSERT INTO PaymentBillDraft (time, operatorID, comment, total, clientID) VALUES ('" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getClientID() + "')";
                statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                resultSet = statement.getGeneratedKeys();
                resultSet.next();
                int key = resultSet.getInt(1);
                for (int i = 0; i < list.size(); i++) {
                    sql = "INSERT INTO TransItem VALUES ('" + key + "', '" + list.get(i).accountID + "', '" + list.get(i).transAmount + "', '" + list.get(i).comment + "')";
                    statement.executeUpdate(sql);
                }
            } else {
                sql = "SELECT PaymentBill FROM DataHelper";
                resultSet = statement.executeQuery(sql);
                resultSet.next();
                int before = resultSet.getInt(1);
                sql = "SELECT COUNT(keyID) FROM PaymentBill";
                resultSet = statement.executeQuery(sql);
                resultSet.next();
                int all = resultSet.getInt(1);
                if (all - before >= 99999)
                    throw new FullException();
                sql = "INSERT INTO PaymentBill (state, time, operatorID, comment, total, clientID) VALUES ('待审批', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getClientID() + "')";
                statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                resultSet = statement.getGeneratedKeys();
                resultSet.next();
                int key = resultSet.getInt(1);
                ID = "FKD-" + new SimpleDateFormat("yyyyMMdd-").format(new Date()) + String.format("%0" + 5 + "d", key - before);
                sql = "UPDATE PaymentBill SET ID='" + ID + "' WHERE keyID=" + key;
                statement.executeUpdate(sql);
                for (int i = 0; i < list.size(); i++) {
                    sql = "INSERT INTO TransItem VALUES ('" + ID + "', '" + list.get(i).accountID + "', '" + list.get(i).transAmount + "', '" + list.get(i).comment + "')";
                    statement.executeUpdate(sql);
                }
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
     * @param po
     * @throws RemoteException,DataException,NotExistException
     */
    @Override
    public synchronized void update(PaymentBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String ID = po.getID();
            String sql;
            ResultSet resultSet;
            if (!"待审批".equals(po.getState())) {
                statement = connection.createStatement();
                sql = "SELECT * FROM PaymentBill WHERE ID='" + ID + "' AND visible=TRUE ";
                resultSet = statement.executeQuery(sql);
                if (!resultSet.next())
                    throw new NotExistException();
                sql = "SELECT visible FROM Client WHERE ID='" + resultSet.getString("clientID") + "' AND visible=TRUE ";
                resultSet = statement.executeQuery(sql);
                if (!resultSet.next()) {
                    sql = "UPDATE PaymentBill SET visible=FALSE WHERE ID='" + ID + "'";
                    statement.executeUpdate(sql);
                    throw new NotExistException();
                }
            }
            sql = "SELECT * FROM TransItem WHERE site_ID='" + ID + "'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String accountID = resultSet.getString("accountID");
                sql = "SELECT * FROM Account WHERE ID='" + accountID + "' AND visible=TRUE ";
                ResultSet temp = statement.executeQuery(sql);
                if (!temp.next()) {
                    sql = "UPDATE PaymentBill SET visible=FALSE WHERE ID='" + ID + "'";
                    statement.executeUpdate(sql);
                    throw new NotExistException();
                }
            }
            sql = "DELETE FROM TransItem WHERE site_ID='" + ID + "'";
            statement.executeUpdate(sql);
            sql = "UPDATE PaymentBill SET state='" + po.getState() + "', comment='" + po.getComment() + "', total='" + po.getTotal() + "', clientID='" + po.getClientID() + "' WHERE ID='" + ID + "'";
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
