package main.java.data.saledata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.FullException;
import main.java.dataservice.saledataservice.SaleTradeBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleTradeBillPO;
import main.java.po.bill.salebill.SaleTradeBillQueryPO;
import main.java.po.goods.GoodsItemPO;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author 陈思彤
 * @description
 * @date 2017/12/13
 */
public class SaleTradeBillData implements SaleTradeBillDataService, Serializable {
    /**
     * @param query [销售单筛选条件]
     * @return 符合筛选条件的销售单
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<SaleTradeBillPO> findsByReport(SaleTradeBillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<SaleTradeBillPO> list;

        try {
            Statement statement = connection.createStatement();
            ArrayList<String> sqlOfQuery = new ArrayList<>();
            String sql;
            ResultSet resultSet;

            sql = "SELECT * FROM User WHERE name='" + query.salesman + "'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String salesmanID = resultSet.getString("ID");
                sql = "SELECT * FROM SaleTradeBill WHERE (salesmanID='" + salesmanID + "'" + (query.start == null ? "" : " OR (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "')") + ") AND state='审批通过'";
                sqlOfQuery.add(sql);
            }
            sql = "SELECT * FROM Client WHERE name='" + query.client + "'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String clientID = resultSet.getString("ID");
                sql = "SELECT * FROM SaleTradeBill WHERE (clientID='" + clientID + "'" + (query.start == null ? "" : " OR (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "')") + ") AND state='审批通过'";
                sqlOfQuery.add(sql);
            }
            list = getList(statement, sqlOfQuery);
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
     * @param query [单据筛选条件]
     * @return 符合筛选条件的销售单
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<SaleTradeBillPO> findsByBill(BillQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<SaleTradeBillPO> list;
        try {
            Statement statement = connection.createStatement();
            ArrayList<String> sqlOfQuery = new ArrayList<>();
            String sql;
            ResultSet resultSet;
            if ("审批不通过".equals(query.state) || "草稿".equals(query.state)) {
                sql = "SELECT * FROM SaleTradeBill WHERE operatorID='" + query.operator + "' AND state='" + query.state + "'";
                sqlOfQuery.add(sql);
            } else {
                sql = "SELECT * FROM User WHERE name='" + query.operator + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String operatorID = resultSet.getString("ID");
                    sql = "SELECT * FROM SaleTradeBill WHERE (operatorID='" + operatorID + "'" + (query.start == null ? "" : " OR (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "')") + ") AND state='" + query.state + "'";
                    sqlOfQuery.add(sql);
                }
                sql = "SELECT * FROM Client WHERE name='" + query.client + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String clientID = resultSet.getString("ID");
                    sql = "SELECT * FROM SaleTradeBill WHERE (clientID='" + clientID + "'" + (query.start == null ? "" : " OR (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "')") + ") AND state='" + query.state + "'";
                    sqlOfQuery.add(sql);
                }
            }
            list = getList(statement, sqlOfQuery);
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
     * @param statement
     * @param sqlOfQuery [筛选条件List]
     * @return 符合筛选条件的销售单
     * @throws SQLException
     */
    private ArrayList<SaleTradeBillPO> getList(Statement statement, ArrayList<String> sqlOfQuery) throws SQLException {
        ArrayList<SaleTradeBillPO> list = new ArrayList<>();
        for (int i = 0; i < sqlOfQuery.size(); i++) {
            String sql = sqlOfQuery.get(i);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String ID = resultSet.getString("ID");
                sql = "SELECT * FROM GoodsItem WHERE site_ID='" + ID + "'";
                ResultSet temp = statement.executeQuery(sql);
                ArrayList<GoodsItemPO> itemPOS = new ArrayList<>();
                while (temp.next()) {
                    itemPOS.add(new GoodsItemPO(temp.getString("goodsID"), temp.getInt("number"), temp.getDouble("price")));
                }
                SaleTradeBillPO saleTradeBillPO = new SaleTradeBillPO(resultSet.getString("state"), resultSet.getTimestamp("time"), resultSet.getString("operatorID"), resultSet.getString("comment"), resultSet.getString("clientID"), resultSet.getString("salesmanID"), itemPOS, resultSet.getString("promotionID"), resultSet.getDouble("totalBeforeDiscount"), resultSet.getDouble("discount"), resultSet.getDouble("amountOfVoucher"), resultSet.getDouble("totalAfterDiscount"));
                saleTradeBillPO.setID(ID);
                list.add(saleTradeBillPO);
            }
        }
        return list;
    }

    /**
     * @param po [销售单]
     * @return 新建销售单ID
     * @throws RemoteException,DataException,FullException
     */
    @Override
    public synchronized String insert(SaleTradeBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT SaleTradeBill FROM DataHelper";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int before = resultSet.getInt(1);
            sql = "SELECT COUNT(keyID) FROM SaleTradeBill";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            int all = resultSet.getInt(1);
            if (all - before >= 99999)
                throw new FullException();
            sql = "INSERT INTO SaleTradeBill (state, time, operatorID, comment, clientID, salesmanID, promotionID, totalBeforeDiscount, discount, amountOfVoucher, totalAfterDiscount) " +
                    "VALUES ('" + po.getState() + "', '" + po.getTime() + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getClientID() + "', '" + po.getSalesmanID() + "', '" + po.getPromotionID() + "', '" + po.getTotalBeforeDiscount() + "', '" + po.getDiscount() + "', '" + po.getAmountOfVoucher() + "', '" + po.getTotalAfterDiscount() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            int key = resultSet.getInt(1);
            String ID = "XSD-" + new SimpleDateFormat("yyyyMMdd-").format(po.getTime()) + String.format("%0" + 5 + "d", key - before);
            sql = "UPDATE SaleTradeBill SET ID='" + ID + "' WHERE keyID=" + key;
            statement.executeUpdate(sql);
            ArrayList<GoodsItemPO> list = po.getSaleList();
            for (int i = 0; i < list.size(); i++) {
                sql = "INSERT INTO GoodsItem VALUES ('" + ID + "', '" + list.get(i).goodsID + "', '" + list.get(i).number + "', '" + list.get(i).price + "')";
                statement.executeUpdate(sql);
            }
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
     * @param po [更新后销售单]
     * @throws RemoteException,DataException
     */
    @Override
    public synchronized void update(SaleTradeBillPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String ID = po.getID();
            String sql = "UPDATE SaleTradeBill SET state='" + po.getState() + "', comment='" + po.getComment() + "', promotionID='" + po.getPromotionID() + "', clientID='" + po.getClientID() + "', salesmanID='" + po.getSalesmanID() + "', totalBeforeDiscount='" + po.getTotalBeforeDiscount() + "', discount='" + po.getDiscount() + "', amountOfVoucher='" + po.getAmountOfVoucher() + "', totalAfterDiscount='" + po.getTotalAfterDiscount() + "' WHERE ID='" + ID + "'";
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
