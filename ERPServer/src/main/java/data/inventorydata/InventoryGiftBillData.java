package main.java.data.inventorydata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;
import main.java.po.goods.GiftItemPO;

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
public class InventoryGiftBillData extends UnicastRemoteObject implements InventoryGiftBillDataService {
    public InventoryGiftBillData() throws RemoteException {
        InventoryGiftBillDataService inventoryGiftBillDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/InventoryGiftBillDataService", inventoryGiftBillDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param query [单据筛选条件]
     * @return 符合筛选条件的库存赠送单
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<InventoryGiftBillPO> finds(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            ArrayList<String> sqlOfQuery = new ArrayList<>();
            ResultSet resultSet;

            {
                String sql;
                if ("审批不通过".equals(query.state) || "草稿".equals(query.state)) {
                    sql = "SELECT * FROM inventorygiftbill WHERE operatorID='" + query.operator + "' AND state='" + query.state + "'";
                    sqlOfQuery.add(sql);
                } else if ("待审批".equals(query.state)) {
                    sql = "SELECT * FROM inventorygiftbill WHERE state='待审批'";
                    sqlOfQuery.add(sql);
                } else {
                    if (query.start == null && query.operator == null && query.client == null) {
                        sql = "SELECT * FROM inventorygiftbill WHERE state='审批通过'";
                        sqlOfQuery.add(sql);
                    } else if (query.start != null) {
                        sql = "SELECT * FROM inventorygiftbill WHERE (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "') AND state='审批通过'";
                        sqlOfQuery.add(sql);
                    } else {
                        sql = "SELECT * FROM User WHERE name='" + query.operator + "'";
                        resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            String operatorID = resultSet.getString("ID");
                            sql = "SELECT * FROM inventorygiftbill WHERE operatorID='" + operatorID + "' AND " + "state = '审批通过'";
                            sqlOfQuery.add(sql);
                        }
                        sql = "SELECT * FROM Client WHERE name='" + query.client + "'";
                        resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            String clientID = resultSet.getString("ID");
                            sql = "SELECT * FROM inventorygiftbill WHERE clientID='" + clientID + "' AND state='审批通过'";
                            sqlOfQuery.add(sql);
                        }
                    }
                }
            }

            ArrayList<InventoryGiftBillPO> list = new ArrayList<>();
            for (String sql : sqlOfQuery) {
                resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    String tempSql = "SELECT * FROM GiftItem WHERE site_ID='" + ID + "'";
                    Statement tempStatement = connection.createStatement();
                    ResultSet tempResult = tempStatement.executeQuery(tempSql);

                    ArrayList<GiftItemPO> itemPOS = new ArrayList<>();
                    while (tempResult.next()) {
                        itemPOS.add(new GiftItemPO(tempResult.getString("goodsID"), tempResult.getInt("number"), tempResult.getDouble("price")));
                    }

                    InventoryGiftBillPO inventoryGiftBillPO = new InventoryGiftBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), resultSet.getString("clientID"), itemPOS, resultSet.getDouble("total"));
                    inventoryGiftBillPO.setID(ID);

                    list.add(inventoryGiftBillPO);
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
     * @param po [库存赠送单]
     * @return 新建库存赠送单的ID
     * @throws RemoteException,DataException,FullException
     */
    @Override
    public synchronized String insert(InventoryGiftBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "SELECT InventoryGiftBill FROM DataHelper";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int before = resultSet.getInt(1);

            sql = "SELECT COUNT(keyID) FROM InventoryGiftBill";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            int all = resultSet.getInt(1);

            if (all - before >= 99999)
                throw new FullException();

            sql = "INSERT INTO InventoryGiftBill (state, time, operatorID, comment, total, clientID) VALUES ('" + po.getState() + "', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getClientID() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "KCZSD-" + new SimpleDateFormat("yyyyMMdd-").format(po.getTime()) + String.format("%0" + 5 + "d", key - before);

            sql = "UPDATE InventoryGiftBill SET ID='" + ID + "' WHERE keyID=" + key;
            statement.executeUpdate(sql);

            ArrayList<GiftItemPO> list = po.getGiftList();
            for (GiftItemPO giftItemPO : list) {
                sql = "INSERT INTO GiftItem VALUES ('" + ID + "', '" + giftItemPO.goodsID + "', '" + giftItemPO.number + "', '" + giftItemPO.price + "')";
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
     * @param po [更新后的库存赠送单]
     * @throws RemoteException,DataException
     */
    @Override
    public synchronized void update(InventoryGiftBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String ID = po.getID();
            String sql = "UPDATE InventoryGiftBill SET state='" + po.getState() + "', comment='" + po.getComment() + "', total='" + po.getTotal() + "', clientID='" + po.getClientID() + "' WHERE ID='" + ID + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            sql = "DELETE FROM GiftItem WHERE site_ID='" + ID + "'";
            statement.executeUpdate(sql);
            ArrayList<GiftItemPO> list = po.getGiftList();
            for (GiftItemPO giftItemPO : list) {
                sql = "INSERT INTO GiftItem VALUES ('" + ID + "', '" + giftItemPO.goodsID + "', '" + giftItemPO.number + "', '" + giftItemPO.price + "')";
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
