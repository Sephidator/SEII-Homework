package main.java.data.saledata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.FullException;
import main.java.dataservice.saledataservice.SaleRefundBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseTradeBillPO;
import main.java.po.bill.salebill.SaleRefundBillPO;
import main.java.po.bill.salebill.SaleTradeBillPO;
import main.java.po.goods.GoodsItemPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author 陈思彤
 * @description
 * @date 2017/12/13
 */
public class SaleRefundBillData implements SaleRefundBillDataService {
    /**
     * @param query [单据筛选条件]
     * @return 符合筛选条件的销售退货单
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<SaleRefundBillPO> finds(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<SaleRefundBillPO> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql;
            SaleRefundBillPO saleRefundBillPO;
            if ("审批不通过".equals(query.state) || "草稿".equals(query.state))
                sql = "SELECT * FROM SaleRefundBill WHERE operatorID='" + query.operatorID + "' AND state='" + query.state + "'";
            else
                sql = "SELECT * FROM SaleRefundBill WHERE state='" + query.state + "'" + (query.start == null ? "" : " OR (time BETWEEN '" + new Timestamp(query.start.getTime()) + "'") + " AND '" + new Timestamp(query.end.getTime()) + "') OR operatorID='" + query.operatorID + "' OR clientID='" + query.clientID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String ID = resultSet.getString("ID");
                sql = "SELECT * FROM GoodsItem WHERE site_ID='" + ID + "'";
                ResultSet temp = statement.executeQuery(sql);
                ArrayList<GoodsItemPO> itemPOS = new ArrayList<>();
                while (temp.next()) {
                    itemPOS.add(new GoodsItemPO(temp.getString("goodsID"), temp.getInt("number"), temp.getDouble("price")));
                }
                temp.close();
                saleRefundBillPO = new SaleRefundBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), resultSet.getString("clientID"), resultSet.getString("salesmanID"), itemPOS, resultSet.getDouble("total"));
                saleRefundBillPO.setID(ID);
                list.add(saleRefundBillPO);
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
     * @param po [销售退货单]
     * @return 新建销售退货单ID
     * @throws RemoteException,DataException,FullException
     */
    @Override
    public synchronized String insert(SaleRefundBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            ArrayList<GoodsItemPO> list = po.getSaleList();
            String sql = "SELECT SaleRefundBill FROM DataHelper";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int before = resultSet.getInt(1);
            sql = "SELECT COUNT(keyID) FROM SaleRefundBill";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            int all = resultSet.getInt(1);
            if (all - before >= 99999)
                throw new FullException();
            sql = "INSERT INTO SaleRefundBill (state, time, operatorID, comment, clientID, salemanID, total) VALUES ('" + po.getState() + "', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getClientID() + "', '" + po.getSalesmanID() + "','" + po.getTotal() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "XSTHD-" + new SimpleDateFormat("yyyyMMdd-").format(po.getTime()) + String.format("%0" + 5 + "d", key - before);
            sql = "UPDATE SaleRefundBill SET ID='" + ID + "' WHERE keyID=" + key;
            statement.executeUpdate(sql);
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
     * @param po [更新后销售退货单]
     * @throws RemoteException,DataException
     */
    @Override
    public synchronized void update(SaleRefundBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String ID = po.getID();
            String sql = "UPDATE SaleRefundBill SET state='" + po.getState() + "', comment='" + po.getComment() + "', total='" + po.getTotal() + "', clientID='" + po.getClientID() + "', salemanID='" + po.getSalesmanID() + "' WHERE ID='" + ID + "'";
            statement.executeUpdate(sql);
            sql = "DELETE FROM GoodsItem WHERE site_ID='" + ID + "'";
            statement.executeUpdate(sql);
            ArrayList<GoodsItemPO> list = po.getSaleList();
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
