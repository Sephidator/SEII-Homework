package main.java.data.promotiondata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.exception.ExistException;
import main.java.exception.NotExistException;
import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.po.goods.GiftItemPO;
import main.java.po.goods.GoodsItemPO;
import main.java.po.promotion.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author 陈思彤
 * @description
 * @date 2017/12/04
 */
public class PromotionData extends UnicastRemoteObject implements PromotionDataService {
    public PromotionData() throws RemoteException {
        PromotionDataService promotionDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/PromotionDataService", promotionDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PromotionPO find(String promotionID) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "SELECT * FROM Promotion WHERE ID='" + promotionID + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();

            return getPromotionPO(resultSet);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    /**
     * @param query
     * @return
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<PromotionPO> finds(PromotionQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = (query == null)
                    ? "SELECT * FROM Promotion WHERE visible=TRUE "
                    : "SELECT * FROM Promotion WHERE (name='" + query.name + "' OR type='" + query.type + "') AND visible=TRUE ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            ArrayList<PromotionPO> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(getPromotionPO(resultSet));
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

    private PromotionPO getPromotionPO(ResultSet resultSet) throws SQLException {
        Connection connection = DataHelper.getConnection();
        try {
            String sql;
            Statement tempStatement = connection.createStatement();
            ResultSet tempResult;

            String promotionID = resultSet.getString("ID"), type = resultSet.getString("type");
            PromotionPO promotionPO;

            if (type.equals("客户促销策略")) {
                sql = "SELECT * FROM GiftItem WHERE site_ID='" + promotionID + "'";
                tempResult = tempStatement.executeQuery(sql);

                ArrayList<GiftItemPO> giftList = new ArrayList<>();
                while (tempResult.next()) {
                    GiftItemPO giftItem = new GiftItemPO(tempResult.getString("goodsID"), tempResult.getInt("number"), tempResult.getDouble("price"));
                    giftList.add(giftItem);
                }
                promotionPO = new PromotionClientPO(resultSet.getString("name"), resultSet.getTimestamp("start"), resultSet.getTimestamp("end"), resultSet.getInt("clientLevel"), resultSet.getDouble("discount"), resultSet.getDouble("voucher"), giftList);
            } else if (type.equals("商品促销策略")) {
                sql = "SELECT * FROM GoodsItem WHERE site_ID='" + promotionID + "'";
                tempResult = tempStatement.executeQuery(sql);

                ArrayList<GoodsItemPO> goodsList = new ArrayList<>();
                while (tempResult.next()) {
                    GoodsItemPO goodsItem = new GoodsItemPO(tempResult.getString("goodsID"), tempResult.getInt("number"), tempResult.getDouble("price"));
                    goodsList.add(goodsItem);
                }
                promotionPO = new PromotionGoodsPO(resultSet.getString("name"), resultSet.getTimestamp("start"), resultSet.getTimestamp("end"), resultSet.getDouble("discount"), goodsList);
            } else {
                sql = "SELECT * FROM GiftItem WHERE site_ID='" + promotionID + "'";
                tempResult = tempStatement.executeQuery(sql);

                ArrayList<GiftItemPO> giftList = new ArrayList<>();
                while (tempResult.next()) {
                    GiftItemPO giftItem = new GiftItemPO(tempResult.getString("goodsID"), tempResult.getInt("number"), tempResult.getDouble("price"));
                    giftList.add(giftItem);
                }
                promotionPO = new PromotionTotalPO(resultSet.getString("name"), resultSet.getTimestamp("start"), resultSet.getTimestamp("end"), resultSet.getDouble("total"), resultSet.getDouble("voucher"), giftList);
            }

            promotionPO.setID(promotionID);
            promotionPO.setVisible(resultSet.getBoolean("visible"));

            return promotionPO;
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * @param po
     * @return
     * @throws RemoteException,DataException,ExistException
     */
    @Override
    public synchronized String insert(PromotionPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "SELECT * FROM Promotion WHERE name='" + po.getName() + "' AND visible=TRUE ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();

            sql = "INSERT INTO Promotion (name, type, start, end) VALUE ('" + po.getName() + "','" + po.getType() + "','" + new Timestamp(po.getStart().getTime()) + "','" + new Timestamp(po.getEnd().getTime()) + "') ";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "Promotion" + String.format("%0" + 8 + "d", key);

            String type = po.getType();
            if (type.equals("客户促销策略")) {
                PromotionClientPO promotionClientPO = (PromotionClientPO) po;
                sql = "UPDATE Promotion SET ID='" + ID + "', clientLevel='" + promotionClientPO.getClientLevel() + "', discount='" + promotionClientPO.getDiscount() + "', voucher='" + promotionClientPO.getVoucher() + "' WHERE keyID =" + key;
                statement.executeUpdate(sql);

                ArrayList<GiftItemPO> list = promotionClientPO.getGiftList();
                for (GiftItemPO giftItemPO : list) {
                    sql = "INSERT INTO GiftItem VALUE ('" + ID + "','" + giftItemPO.goodsID + "','" + giftItemPO.number + "','" + giftItemPO.price + "')";
                    statement.executeUpdate(sql);
                }
            } else if (type.equals("商品促销策略")) {
                PromotionGoodsPO promotionGoodsPO = (PromotionGoodsPO) po;
                sql = "UPDATE Promotion SET ID='" + ID + "', discount='" + promotionGoodsPO.getDiscount() + "' WHERE keyID=" + key;
                statement.executeUpdate(sql);

                ArrayList<GoodsItemPO> list = promotionGoodsPO.getGoodsList();
                for (GoodsItemPO goodsItemPO : list) {
                    sql = "INSERT INTO GoodsItem VALUES ('" + ID + "', '" + goodsItemPO.goodsID + "', '" + goodsItemPO.number + "', '" + goodsItemPO.price + "')";
                    statement.executeUpdate(sql);
                }
            } else {
                PromotionTotalPO promotionTotalPO = (PromotionTotalPO) po;
                sql = "UPDATE Promotion SET ID='" + ID + "', total='" + promotionTotalPO.getTotal() + "', voucher='" + promotionTotalPO.getVoucher() + "' WHERE keyID=" + key;
                statement.executeUpdate(sql);

                ArrayList<GiftItemPO> list = promotionTotalPO.getGiftList();
                for (GiftItemPO giftItemPO : list) {
                    sql = "INSERT INTO GiftItem VALUE ('" + ID + "','" + giftItemPO.goodsID + "','" + giftItemPO.number + "','" + giftItemPO.price + "')";
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
     * @param promotionID
     * @throws RemoteException,DataException,NotExistException
     */
    @Override
    public synchronized void delete(String promotionID) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "SELECT * FROM Promotion WHERE ID='" + promotionID + "' AND visible=TRUE ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();

            sql = "UPDATE Promotion SET visible=FALSE WHERE ID='" + promotionID + "'";
            statement.executeUpdate(sql);

            resultSet.close();
            statement.close();
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
     * @throws RemoteException,DataException,NotExistException,ExistException
     */
    @Override
    public synchronized void update(PromotionPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String ID = po.getID();
            String sql = "SELECT * FROM Promotion WHERE ID='" + ID + "' AND visible=TRUE ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();

            sql = "SELECT * FROM Promotion WHERE ID<>'" + ID + "' AND name = '" + po.getName() + "' AND visible = TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();

            String type = po.getType();

            if (type.equals("客户促销策略")) {
                PromotionClientPO promotionClientPO = (PromotionClientPO) po;
                sql = "UPDATE Promotion SET clientLevel='" + promotionClientPO.getClientLevel() + "', discount='" + promotionClientPO.getDiscount() + "', voucher='" + promotionClientPO.getVoucher() + "' WHERE ID ='" + ID + "'";
                statement.executeUpdate(sql);

                sql = "DELETE FROM GiftItem WHERE site_ID='" + ID + "'";
                statement.executeUpdate(sql);
                ArrayList<GiftItemPO> list = promotionClientPO.getGiftList();
                for (GiftItemPO giftItemPO : list) {
                    sql = "INSERT INTO GiftItem VALUE ('" + ID + "','" + giftItemPO.goodsID + "','" + giftItemPO.number + "','" + giftItemPO.price + "')";
                    statement.executeUpdate(sql);
                }
            } else if (type.equals("商品促销策略")) {
                PromotionGoodsPO promotionGoodsPO = (PromotionGoodsPO) po;
                sql = "UPDATE Promotion SET discount='" + promotionGoodsPO.getDiscount() + "' WHERE ID='" + ID + "'";
                statement.executeUpdate(sql);

                sql = "DELETE FROM GoodsItem WHERE site_ID='" + ID + "'";
                statement.executeUpdate(sql);
                ArrayList<GoodsItemPO> list = promotionGoodsPO.getGoodsList();
                for (GoodsItemPO goodsItemPO : list) {
                    sql = "INSERT INTO GoodsItem VALUES ('" + ID + "', '" + goodsItemPO.goodsID + "', '" + goodsItemPO.number + "', '" + goodsItemPO.price + "')";
                    statement.executeUpdate(sql);
                }
            } else {
                PromotionTotalPO promotionTotalPO = (PromotionTotalPO) po;
                sql = "UPDATE Promotion SET total='" + promotionTotalPO.getTotal() + "', voucher='" + promotionTotalPO.getVoucher() + "' WHERE ID='" + ID + "'";
                statement.executeUpdate(sql);

                sql = "DELETE FROM GiftItem WHERE site_ID='" + ID + "'";
                statement.executeUpdate(sql);
                ArrayList<GiftItemPO> list = promotionTotalPO.getGiftList();
                for (GiftItemPO giftItemPO : list) {
                    sql = "INSERT INTO GiftItem VALUE ('" + ID + "','" + giftItemPO.goodsID + "','" + giftItemPO.number + "','" + giftItemPO.price + "')";
                    statement.executeUpdate(sql);
                }
            }

            resultSet.close();
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
