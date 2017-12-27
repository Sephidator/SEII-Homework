package main.java.data.purchasedata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.dataservice.purchasedataservice.PurchaseTradeBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseTradeBillPO;
import main.java.po.goods.GoodsItemPO;

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
 * @date 2017/12/13
 */
public class PurchaseTradeBillData extends UnicastRemoteObject implements PurchaseTradeBillDataService {
    public PurchaseTradeBillData() throws RemoteException {
        PurchaseTradeBillDataService purchaseTradeBillDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/PurchaseTradeBillDataService", purchaseTradeBillDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param query [单据筛选条件]
     * @return 符合筛选条件的进货单
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<PurchaseTradeBillPO> finds(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<PurchaseTradeBillPO> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ArrayList<String> sqlOfQuery = new ArrayList<>();
            String sql;
            ResultSet resultSet;
            if ("审批不通过".equals(query.state) || "草稿".equals(query.state)) {
                sql = "SELECT * FROM purchasetradebill WHERE operatorID='" + query.operator + "' AND state='" + query.state + "'";
                sqlOfQuery.add(sql);
            } else if ("待审批".equals(query.state)) {
                sql = "SELECT * FROM purchasetradebill WHERE state='待审批'";
                sqlOfQuery.add(sql);
            } else {
                if (query.start != null) {
                    sql = "SELECT * FROM purchasetradebill WHERE (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "') AND state='审批通过'";
                    sqlOfQuery.add(sql);
                } else {
                    sql = "SELECT * FROM User WHERE name='" + query.operator + "'";
                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        String operatorID = resultSet.getString("ID");
                        sql = "SELECT * FROM purchasetradebill WHERE operatorID='" + operatorID + "' AND " + "state = '审批通过'";
                        sqlOfQuery.add(sql);
                    }
                    sql = "SELECT * FROM Client WHERE name='" + query.client + "'";
                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        String clientID = resultSet.getString("ID");
                        sql = "SELECT * FROM purchasetradebill WHERE clientID='" + clientID + "' AND state='审批通过'";
                        sqlOfQuery.add(sql);
                    }
                }
            }
            for (int i = 0; i < sqlOfQuery.size(); i++) {
                sql = sqlOfQuery.get(i);
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    sql = "SELECT * FROM GoodsItem WHERE site_ID='" + ID + "'";
                    ResultSet temp = connection.createStatement().executeQuery(sql);
                    ArrayList<GoodsItemPO> itemPOS = new ArrayList<>();
                    while (temp.next()) {
                        itemPOS.add(new GoodsItemPO(temp.getString("goodsID"), temp.getInt("number"), temp.getDouble("price")));
                    }
                    temp.close();
                    PurchaseTradeBillPO purchaseTradeBillPO = new PurchaseTradeBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), resultSet.getString("clientID"), itemPOS, resultSet.getDouble("total"));
                    purchaseTradeBillPO.setID(ID);
                    list.add(purchaseTradeBillPO);
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
     * @param po [j进货单]
     * @return 新建进货单帐号
     * @throws RemoteException,DataException,FullException
     */
    @Override
    public synchronized String insert(PurchaseTradeBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT PurchaseTradeBill FROM DataHelper";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int before = resultSet.getInt(1);
            sql = "SELECT COUNT(keyID) FROM PurchaseTradeBill";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            int all = resultSet.getInt(1);
            if (all - before >= 99999)
                throw new FullException();
            sql = "INSERT INTO PurchaseTradeBill (state, time, operatorID, comment, total, clientID) VALUES ('" + po.getState() + "', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getClientID() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "JHD-" + new SimpleDateFormat("yyyyMMdd-").format(po.getTime()) + String.format("%0" + 5 + "d", key - before);
            sql = "UPDATE PurchaseTradeBill SET ID='" + ID + "' WHERE keyID=" + key;
            statement.executeUpdate(sql);
            ArrayList<GoodsItemPO> list = po.getPurchaseList();
            for (int i = 0; i < list.size(); i++) {
                sql = "INSERT INTO GoodsItem VALUES ('" + ID + "', '" + list.get(i).goodsID + "', '" + list.get(i).number + "', '" + list.get(i).price + "')";
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
     * @param po [更新后进货单]
     * @throws RemoteException,DataException
     */
    @Override
    public void update(PurchaseTradeBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String ID = po.getID();
            String sql = "UPDATE PurchaseTradeBill SET state='" + po.getState() + "', comment='" + po.getComment() + "', total='" + po.getTotal() + "', clientID='" + po.getClientID() + "' WHERE ID='" + ID + "'";
            statement.executeUpdate(sql);
            sql = "DELETE FROM GoodsItem WHERE site_ID='" + ID + "'";
            statement.executeUpdate(sql);
            ArrayList<GoodsItemPO> list = po.getPurchaseList();
            for (int i = 0; i < list.size(); i++) {
                sql = "INSERT INTO GoodsItem VALUES ('" + ID + "', '" + list.get(i).goodsID + "', '" + list.get(i).number + "', '" + list.get(i).price + "')";
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
