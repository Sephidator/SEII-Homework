package main.java.data.inventorydata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.FullException;
import main.java.dataservice.inventorydataservice.InventoryLossOverBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;
import main.java.po.bill.inventorybill.LossOverItemPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author 陈思彤
 * @description
 * @date 2017/12/12
 */
public class InventoryLossOverBillData implements InventoryLossOverBillDataService {
    /**
     * @param query [单据筛选条件]
     * @return 符合筛选条件的库存溢损单
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<InventoryLossOverBillPO> finds(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<InventoryLossOverBillPO> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            String sql;
            InventoryLossOverBillPO inventoryLossOverBillPO;
            if ("审批不通过".equals(query.state) || "草稿".equals(query.state))
                sql = "SELECT * FROM InventoryLossOverBill WHERE operatorID='" + query.operatorID + "' AND state='" + query.state + "'";
            else {
                sql = "SELECT * FROM InventoryLossOverBill WHERE state='" + query.state + "'" + (query.start == null ? "" : " OR (time BETWEEN '" + new Timestamp(query.start.getTime()) + "'") + " AND '" + new Timestamp(query.end.getTime()) + "') OR operatorID='" + query.operatorID + "'";
            }
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String ID = resultSet.getString("ID");
                sql = "SELECT * FROM LossOverItem WHERE site_ID='" + ID + "'";
                ResultSet temp = statement.executeQuery(sql);
                ArrayList<LossOverItemPO> itemPOS = new ArrayList<>();
                while (temp.next()) {
                    itemPOS.add(new LossOverItemPO(temp.getString("goodsID"), temp.getDouble("price"), temp.getInt("goodsNumber"), temp.getInt("actualNumber")));
                }
                inventoryLossOverBillPO = new InventoryLossOverBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), itemPOS);
                inventoryLossOverBillPO.setID(ID);
                list.add(inventoryLossOverBillPO);
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
     * @param po [库存溢损单]
     * @return 新建库存溢损单的ID
     * @throws RemoteException,DataException
     */
    @Override
    public synchronized String insert(InventoryLossOverBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            ArrayList<LossOverItemPO> list = po.getLossOverList();
            String sql = "SELECT InventoryLossOverBill FROM DataHelper";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int before = resultSet.getInt(1);
            sql = "SELECT COUNT(keyID) FROM InventoryLossOverBill";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            int all = resultSet.getInt(1);
            if (all - before >= 99999)
                throw new FullException();
            sql = "INSERT INTO InventoryLossOverBill (state, time, operatorID, comment) VALUES ('" + po.getState() + "', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "KCYSD-" + new SimpleDateFormat("yyyyMMdd-").format(po.getTime()) + String.format("%0" + 5 + "d", key - before);
            sql = "UPDATE InventoryLossOverBill SET ID='" + ID + "' WHERE keyID=" + key;
            statement.executeUpdate(sql);
            for (int i = 0; i < list.size(); i++) {
                sql = "INSERT INTO LossOverItem VALUES ('" + ID + "', '" + list.get(i).goodsID + "', '" + list.get(i).price + "', '" + list.get(i).goodsNumber + "', '" + list.get(i).actualNumber + "')";
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
     * @param po [更新后库存溢损单]
     * @throws RemoteException,DataException
     */
    @Override
    public synchronized void update(InventoryLossOverBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String ID = po.getID();
            String sql = "UPDATE InventoryLossOverBill SET state='" + po.getState() + "', comment='" + po.getComment() + "' WHERE ID='" + ID + "'";
            statement.executeUpdate(sql);
            sql = "DELETE FROM LossOverItem WHERE site_ID='" + ID + "'";
            statement.executeUpdate(sql);
            ArrayList<LossOverItemPO> list = po.getLossOverList();
            for (int i = 0; i < list.size(); i++) {
                sql = "INSERT INTO LossOverItem VALUES ('" + ID + "', '" + list.get(i).goodsID + "', '" + list.get(i).price + "', '" + list.get(i).goodsNumber + "', '" + list.get(i).actualNumber + "')";
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