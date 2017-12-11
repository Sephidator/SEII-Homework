package main.java.data.inventorydata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.FullException;
import main.java.data.datautility.NotExistException;
import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;
import main.java.po.goods.GiftItemPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author CST
 * @description Implements InventoryGiftBillDataService
 * @date 2017/12/10 下午5:52
 */
public class InventoryGiftBillData implements InventoryGiftBillDataService {
    /**
     * @param query
     * @return ArrayList<InventoryGiftBillPO>
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<InventoryGiftBillPO> finds(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<InventoryGiftBillPO> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            String sql;
            InventoryGiftBillPO inventoryGiftBillPO;
            if ("草稿".equals(query.state)) {
                sql = "SELECT * FROM InventoryGiftBillDraft WHERE operatorID='" + query.operatorID + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    sql = "SELECT * FROM GiftItem WHERE site_ID='" + ID + "'";
                    ResultSet temp = statement.executeQuery(sql);
                    ArrayList<GiftItemPO> itemPOS = new ArrayList<>();
                    while (temp.next()) {
                        itemPOS.add(new GiftItemPO(temp.getString("goodsID"), temp.getInt("number"), temp.getDouble("price")));
                    }
                    inventoryGiftBillPO = new InventoryGiftBillPO("草稿", resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), resultSet.getString("clientID"), itemPOS, resultSet.getDouble("total"));
                    list.add(inventoryGiftBillPO);
                }
            } else {
                if ("审批不通过".equals(query.state))
                    sql = "SELECT * FROM InventoryGiftBill WHERE operatorID='" + query.operatorID + "' AND state='审批不通过'";
                else if (query.start != null && query.end != null)
                    sql = "SELECT * FROM InventoryGiftBill WHERE visible=TRUE AND state='" + query.state + "' AND (time BETWEEN '" + query.start + "' AND '" + query.end + "')";
                else if (query.operatorID != null)
                    sql = "SELECT * FROM InventoryGiftBill WHERE visible=TRUE AND state='" + query.state + "' AND operatorID='" + query.operatorID + "'";
                else
                    sql = "SELECT * FROM InventoryGiftBill WHERE visible=TRUE AND state='" + query.state + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    sql = "SELECT * FROM GiftItem WHERE site_ID='" + ID + "'";
                    ResultSet temp = statement.executeQuery(sql);
                    ArrayList<GiftItemPO> itemPOS = new ArrayList<>();
                    while (temp.next()) {
                        itemPOS.add(new GiftItemPO(temp.getString("goodsID"), temp.getInt("number"), temp.getDouble("price")));
                    }
                    inventoryGiftBillPO = new InventoryGiftBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), resultSet.getString("clientID"), itemPOS, resultSet.getDouble("total"));
                    inventoryGiftBillPO.setID(ID);
                    list.add(inventoryGiftBillPO);
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
    public String insert(InventoryGiftBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql;
            ResultSet resultSet;
            ArrayList<GiftItemPO> list = po.getGiftList();
            String ID = "Success";
            if ("草稿".equals(po.getState())) {
                sql = "INSERT INTO InventoryGiftBillDraft (time, operatorID, comment, total, clientID) VALUES ('" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getClientID() + "')";
                statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                resultSet = statement.getGeneratedKeys();
                resultSet.next();
                int key = resultSet.getInt(1);
                for (int i = 0; i < list.size(); i++) {
                    sql = "INSERT INTO GiftItem VALUES ('" + key + "', '" + list.get(i).goodsID + "', '" + list.get(i).number + "')";
                    statement.executeUpdate(sql);
                }
            } else {
                sql = "SELECT InventoryGiftBill FROM DataHelper";
                resultSet = statement.executeQuery(sql);
                resultSet.next();
                int before = resultSet.getInt(1);
                sql = "SELECT COUNT(keyID) FROM InventoryGiftBill";
                resultSet = statement.executeQuery(sql);
                resultSet.next();
                int all = resultSet.getInt(1);
                if (all - before >= 99999)
                    throw new FullException();
                sql = "INSERT INTO InventoryGift (state, time, operatorID, comment, total, clientID) VALUES ('待审批', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getClientID() + "')";
                statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                resultSet = statement.getGeneratedKeys();
                resultSet.next();
                int key = resultSet.getInt(1);
                ID = "KCZSD-" + new SimpleDateFormat("yyyyMMdd-").format(new Date()) + String.format("%0" + 5 + "d", key - before);
                sql = "UPDATE InventoryGiftBill SET ID='" + ID + "' WHERE keyID=" + key;
                statement.executeUpdate(sql);
                for (int i = 0; i < list.size(); i++) {
                    sql = "INSERT INTO GiftItem VALUES ('" + ID + "', '" + list.get(i).goodsID + "', '" + list.get(i).number + "', '" + list.get(i).price + "')";
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
    public void update(InventoryGiftBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String ID = po.getID();
            String sql;
            ResultSet resultSet;
            if (!"待审批".equals(po.getState())) {
                statement = connection.createStatement();
                sql = "SELECT * FROM InventoryGiftBill WHERE ID='" + ID + "' AND visible=TRUE ";
                resultSet = statement.executeQuery(sql);
                if (!resultSet.next())
                    throw new NotExistException();
                sql = "SELECT visible FROM Client WHERE ID='" + resultSet.getString("clientID") + "' AND visible=TRUE ";
                resultSet = statement.executeQuery(sql);
                if (!resultSet.next()) {
                    sql = "UPDATE InventoryGiftBill SET visible=FALSE WHERE ID='" + ID + "'";
                    statement.executeUpdate(sql);
                    throw new NotExistException();
                }
            }
            sql = "SELECT * FROM GiftItem WHERE site_ID='" + ID + "'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String goodsID = resultSet.getString("goodsID");
                sql = "SELECT * FROM Goods WHERE ID='" + goodsID + "' AND visible=TRUE ";
                ResultSet temp = statement.executeQuery(sql);
                if (!temp.next()) {
                    sql = "UPDATE InventoryGiftBill SET visible=FALSE WHERE ID='" + ID + "'";
                    statement.executeUpdate(sql);
                    throw new NotExistException();
                }
            }
            sql = "DELETE FROM GiftItem WHERE site_ID='" + ID + "'";
            statement.executeUpdate(sql);
            sql = "UPDATE InventoryGiftBill SET state='" + po.getState() + "', comment='" + po.getComment() + "', total='" + po.getTotal() + "', clientID='" + po.getClientID() + "' WHERE ID='" + ID + "'";
            statement.executeUpdate(sql);
            ArrayList<GiftItemPO> list = po.getGiftList();
            for (int i = 0; i < list.size(); i++) {
                sql = "INSERT INTO GiftItem VALUES ('" + ID + "', '" + list.get(i).goodsID + "', '" + list.get(i).number + "', '" + list.get(i).price + "')";
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
