package main.java.data.saledata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.exception.FullException;
import main.java.dataservice.saledataservice.SaleTradeBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.salebill.SaleTradeBillPO;
import main.java.po.bill.salebill.SaleTradeBillQueryPO;
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
public class SaleTradeBillData extends UnicastRemoteObject implements SaleTradeBillDataService {
    public SaleTradeBillData() throws RemoteException {
        SaleTradeBillDataService saleTradeBillDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/SaleTradeBillDataService", saleTradeBillDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

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
            ArrayList<String> sqlOfQuery = new ArrayList<>();
            String sql;
            Statement statement = connection.createStatement();
            ResultSet resultSet;

            if (query == null) {
                sql = "SELECT * FROM saletradebill WHERE state='审批通过'";
                sqlOfQuery.add(sql);
            } else {
                if (query.start != null) {
                    sql = "SELECT * FROM saletradebill WHERE (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "') AND state='审批通过'";
                    sqlOfQuery.add(sql);
                } else {
                    sql = "SELECT * FROM User WHERE name='" + query.salesman + "'";
                    resultSet = statement.executeQuery(sql);

                    while (resultSet.next()) {
                        String operatorID = resultSet.getString("ID");
                        sql = "SELECT * FROM saletradebill WHERE operatorID='" + operatorID + "' AND " + "state = '审批通过'";
                        sqlOfQuery.add(sql);
                    }

                    sql = "SELECT * FROM Client WHERE name='" + query.client + "'";
                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        String clientID = resultSet.getString("ID");
                        sql = "SELECT * FROM saletradebill WHERE clientID='" + clientID + "' AND state='审批通过'";
                        sqlOfQuery.add(sql);
                    }

                    sql = "SELECT * FROM goods WHERE name='" + query.goodsName + "'";
                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        String goodsID = resultSet.getString("ID");
                        sql = "SELECT * FROM goodsitem WHERE goodsID='" + goodsID + "' AND site_ID LIKE '%XSD%'";
                        ResultSet temp = connection.createStatement().executeQuery(sql);
                        while (temp.next()) {
                            String ID = temp.getString("site_ID");
                            sql = "SELECT * FROM saletradebill WHERE ID='" + ID + "' AND state='审批通过'";
                            sqlOfQuery.add(sql);
                        }
                    }
                }
            }

            list = getList(sqlOfQuery);
            statement.close();
            return list;
        } catch (SQLException e) {
            try {
                e.printStackTrace();
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

        try {
            Statement statement = connection.createStatement();
            ArrayList<String> sqlOfQuery = new ArrayList<>();
            String sql;
            ResultSet resultSet;
            if ("审批不通过".equals(query.state) || "草稿".equals(query.state)) {
                sql = "SELECT * FROM saletradebill WHERE operatorID='" + query.operator + "' AND state='" + query.state + "'";
                sqlOfQuery.add(sql);
            } else if ("待审批".equals(query.state)) {
                sql = "SELECT * FROM saletradebill WHERE state='待审批'";
                sqlOfQuery.add(sql);
            } else {
                if (query.start == null && query.operator == null && query.client == null) {
                    sql = "SELECT * FROM saletradebill WHERE state='审批通过'";
                    sqlOfQuery.add(sql);
                } else if (query.start != null) {
                    sql = "SELECT * FROM saletradebill WHERE (time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "') AND state='审批通过'";
                    sqlOfQuery.add(sql);
                } else {
                    sql = "SELECT * FROM User WHERE name='" + query.operator + "'";
                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        String operatorID = resultSet.getString("ID");
                        sql = "SELECT * FROM saletradebill WHERE operatorID='" + operatorID + "' AND " + "state = '审批通过'";
                        sqlOfQuery.add(sql);
                    }
                    sql = "SELECT * FROM Client WHERE name='" + query.client + "'";
                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        String clientID = resultSet.getString("ID");
                        sql = "SELECT * FROM saletradebill WHERE clientID='" + clientID + "' AND state='审批通过'";
                        sqlOfQuery.add(sql);
                    }
                }
            }

            ArrayList<SaleTradeBillPO> list = getList(sqlOfQuery);
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
     * @param sqlOfQuery [筛选条件List]
     * @return 符合筛选条件的销售单
     * @throws SQLException
     */
    private ArrayList<SaleTradeBillPO> getList(ArrayList<String> sqlOfQuery) throws SQLException {
        Connection connection = DataHelper.getConnection();
        Statement statement = connection.createStatement();

        ArrayList<SaleTradeBillPO> list = new ArrayList<>();
        for (int i = 0; i < sqlOfQuery.size(); i++) {
            String sql = sqlOfQuery.get(i);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String ID = resultSet.getString("ID");
                sql = "SELECT * FROM GoodsItem WHERE site_ID='" + ID + "'";
                ResultSet temp = connection.createStatement().executeQuery(sql);

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
            String sql = "SELECT SaleTradeBill FROM DataHelper";
            Statement statement = connection.createStatement();
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
                    "VALUES ('" + po.getState() + "', '" + new Timestamp(po.getTime().getTime()) + "', '" + po.getOperatorID() + "', '" + po.getComment() + "', '" + po.getClientID() + "', '" + po.getSalesmanID() + "', '" + po.getPromotionID() + "', '" + po.getTotalBeforeDiscount() + "', '" + po.getDiscount() + "', '" + po.getAmountOfVoucher() + "', '" + po.getTotalAfterDiscount() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            resultSet = statement.getGeneratedKeys();
            resultSet.next();
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
            String ID = po.getID();
            String sql = "UPDATE SaleTradeBill SET state='" + po.getState() + "', comment='" + po.getComment() + "', promotionID='" + po.getPromotionID() + "', clientID='" + po.getClientID() + "', salesmanID='" + po.getSalesmanID() + "', totalBeforeDiscount='" + po.getTotalBeforeDiscount() + "', discount='" + po.getDiscount() + "', amountOfVoucher='" + po.getAmountOfVoucher() + "', totalAfterDiscount='" + po.getTotalAfterDiscount() + "' WHERE ID='" + ID + "'";
            Statement statement = connection.createStatement();
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
