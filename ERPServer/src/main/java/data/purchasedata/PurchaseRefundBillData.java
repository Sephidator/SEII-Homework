package main.java.data.purchasedata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.FullException;
import main.java.dataservice.purchasedataservice.PurchaseRefundBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseRefundBillPO;
import main.java.po.goods.GoodsItemPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author 陈思彤
 * @description
 * @date 2017/12/12
 */
public class PurchaseRefundBillData implements PurchaseRefundBillDataService {
    /**
     * @param query [单据筛选条件]
     * @return 符合筛选条件的进货退货单
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<PurchaseRefundBillPO> finds(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<PurchaseRefundBillPO> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ArrayList<String> sqlOfQuery = new ArrayList<>();
            String sql;
            ResultSet resultSet;
            if ("审批不通过".equals(query.state) || "草稿".equals(query.state)) {
                sql = "SELECT * FROM PurchaseRefundBill WHERE operatorID='" + query.operator + "' AND state='" + query.state + "'";
                sqlOfQuery.add(sql);
            } else {
                sql = "SELECT * FROM User WHERE name='" + query.operator + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String operatorID = resultSet.getString("ID");
                    sql = "SELECT * FROM PurchaseRefundBill WHERE (operatorID='" + operatorID + "'" + (query.start == null ? "" : " OR (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "')") + ") AND state='" + query.state + "'";
                    sqlOfQuery.add(sql);
                }
                sql = "SELECT * FROM Client WHERE name='" + query.client + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String clientID = resultSet.getString("ID");
                    sql = "SELECT * FROM PurchaseRefundBill WHERE (clientID='" + clientID + "'" + (query.start == null ? "" : " OR (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "')") + ") AND state='" + query.state + "'";
                    sqlOfQuery.add(sql);
                }
            }
            for (int i = 0; i < sqlOfQuery.size(); i++) {
                sql = sqlOfQuery.get(i);
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String ID = resultSet.getString("ID");
                    sql = "SELECT * FROM GoodsItem WHERE site_ID='" + ID + "'";
                    ResultSet temp = statement.executeQuery(sql);
                    ArrayList<GoodsItemPO> itemPOS = new ArrayList<>();
                    while (temp.next()) {
                        itemPOS.add(new GoodsItemPO(temp.getString("goodsID"), temp.getInt("number"), temp.getDouble("price")));
                    }
                    temp.close();
                    PurchaseRefundBillPO purchaseRefundBillPO = new PurchaseRefundBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), resultSet.getString("clientID"), itemPOS, resultSet.getDouble("total"));
                    purchaseRefundBillPO.setID(ID);
                    list.add(purchaseRefundBillPO);
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
     * @param po [进货退货单]
     * @return 新建进货退货单ID
     * @throws RemoteException,DataException,FullException
     */
    @Override
    public synchronized String insert(PurchaseRefundBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT PurchaseRefundBill FROM DataHelper";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int before = resultSet.getInt(1);
            sql = "SELECT COUNT(keyID) FROM PurchaseRefundBill";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            int all = resultSet.getInt(1);
            if (all - before >= 99999)
                throw new FullException();
            sql = "INSERT INTO PurchaseRefundBill (state, time, operatorID, comment, total, clientID) VALUES ('" + po.getState() + "', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getTotal() + "', '" + po.getClientID() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "JHTHD-" + new SimpleDateFormat("yyyyMMdd-").format(po.getTime()) + String.format("%0" + 5 + "d", key - before);
            sql = "UPDATE PurchaseRefundBill SET ID='" + ID + "' WHERE keyID=" + key;
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
     * @param po [更新后进货退货单]
     * @throws RemoteException,DataException
     */
    @Override
    public synchronized void update(PurchaseRefundBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String ID = po.getID();
            String sql = "UPDATE PurchaseRefundBill SET state='" + po.getState() + "', comment='" + po.getComment() + "', total='" + po.getTotal() + "', clientID='" + po.getClientID() + "' WHERE ID='" + ID + "'";
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
