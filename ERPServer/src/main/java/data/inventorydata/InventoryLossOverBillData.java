package main.java.data.inventorydata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.dataservice.inventorydataservice.InventoryLossOverBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;
import main.java.po.bill.inventorybill.LossOverItemPO;

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
 * @date 2017/12/12
 */
public class InventoryLossOverBillData extends UnicastRemoteObject implements InventoryLossOverBillDataService {
    public InventoryLossOverBillData() throws RemoteException {
        InventoryLossOverBillDataService inventoryLossOverBillDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/InventoryLossOverBillDataService", inventoryLossOverBillDataService);
        } catch (MalformedURLException e) {
        }
    }

    /**
     * @param query [单据筛选条件]
     * @return 符合筛选条件的库存溢损单
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<InventoryLossOverBillPO> finds(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            ArrayList<String> sqlOfQuery = new ArrayList<>();
            ResultSet resultSet;

            {
                String sql;
                if ("审批不通过".equals(query.state) || "草稿".equals(query.state)) {
                    sql = "SELECT * FROM inventorylossoverbill WHERE operatorID='" + query.operator + "' AND state='" + query.state + "'";
                    sqlOfQuery.add(sql);
                } else if ("待审批".equals(query.state)) {
                    sql = "SELECT * FROM inventorylossoverbill WHERE state='待审批'";
                    sqlOfQuery.add(sql);
                } else {
                    if (query.start == null && query.operator == null && query.client == null) {
                        sql = "SELECT * FROM inventorylossoverbill WHERE state='审批通过'";
                        sqlOfQuery.add(sql);
                    } else if (query.start != null) {
                        sql = "SELECT * FROM inventorylossoverbill WHERE (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "') AND state='审批通过'";
                        sqlOfQuery.add(sql);
                    } else {
                        sql = "SELECT * FROM User WHERE name='" + query.operator + "'";
                        resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            String operatorID = resultSet.getString("ID");
                            sql = "SELECT * FROM inventorylossoverbill WHERE operatorID='" + operatorID + "' AND " + "state = '审批通过'";
                            sqlOfQuery.add(sql);
                        }
                    }
                }
            }

            ArrayList<InventoryLossOverBillPO> list = new ArrayList<>();
            for (String sql : sqlOfQuery) {
                resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    String tempSql = "SELECT * FROM LossOverItem WHERE site_ID='" + ID + "'";
                    ResultSet tempResult = connection.createStatement().executeQuery(tempSql);

                    ArrayList<LossOverItemPO> itemPOS = new ArrayList<>();
                    while (tempResult.next()) {
                        itemPOS.add(new LossOverItemPO(tempResult.getString("goodsID"), tempResult.getDouble("price"), tempResult.getInt("goodsNumber"), tempResult.getInt("actualNumber")));
                    }

                    InventoryLossOverBillPO inventoryLossOverBillPO = new InventoryLossOverBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), itemPOS);
                    inventoryLossOverBillPO.setID(ID);
                    list.add(inventoryLossOverBillPO);
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
     * @param po [库存溢损单]
     * @return 新建库存溢损单的ID
     * @throws RemoteException,DataException
     */
    @Override
    public synchronized String insert(InventoryLossOverBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "SELECT InventoryLossOverBill FROM DataHelper";
            Statement statement = connection.createStatement();
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

            ArrayList<LossOverItemPO> list = po.getLossOverList();
            for (LossOverItemPO lossOverItemPO : list) {
                sql = "INSERT INTO LossOverItem VALUES ('" + ID + "', '" + lossOverItemPO.goodsID + "', '" + lossOverItemPO.price + "', '" + lossOverItemPO.goodsNumber + "', '" + lossOverItemPO.actualNumber + "')";
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
            String ID = po.getID();
            String sql = "UPDATE InventoryLossOverBill SET state='" + po.getState() + "', comment='" + po.getComment() + "' WHERE ID='" + ID + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            sql = "DELETE FROM LossOverItem WHERE site_ID='" + ID + "'";
            statement.executeUpdate(sql);
            ArrayList<LossOverItemPO> list = po.getLossOverList();
            for (LossOverItemPO lossOverItemPO : list) {
                sql = "INSERT INTO LossOverItem VALUES ('" + ID + "', '" + lossOverItemPO.goodsID + "', '" + lossOverItemPO.price + "', '" + lossOverItemPO.goodsNumber + "', '" + lossOverItemPO.actualNumber + "')";
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
