package main.java.data.promotiondata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.ExistException;
import main.java.data.datautility.NotExistException;
import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.po.goods.GiftItemPO;
import main.java.po.goods.GoodsItemPO;
import main.java.po.promotion.*;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class PromotionData implements PromotionDataService {
    @Override
    public PromotionPO find(String promotionID) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Promotion WHERE ID='" + promotionID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return getPromotionPO(statement, resultSet, promotionID, resultSet.getString("type"));
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    @Override
    public ArrayList<PromotionPO> finds(PromotionQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<PromotionPO> list = new ArrayList<>();
        String sql;
        if (query == null)
            sql = "SELECT * FROM Promotion WHERE visible=TRUE ";
        else
            sql = "SELECT * FROM Promotion WHERE (name='" + query.name + "' OR type='" + query.type + "') AND visible=TRUE ";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(getPromotionPO(statement, resultSet, resultSet.getString("ID"), resultSet.getString("type")));
            }
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    private PromotionPO getPromotionPO(Statement statement, ResultSet resultSet, String promotionID, String type) throws SQLException {
        PromotionPO promotionPO;
        String sql;
        try {
            if (type.equals("客户促销策略")) {
                ArrayList<GiftItemPO> giftList = new ArrayList<>();
                GiftItemPO giftItem;
                sql = "SELECT * FROM GiftItem WHERE site_ID='" + promotionID + "'";
                ResultSet temp = statement.executeQuery(sql);
                while (temp.next()) {
                    giftItem = new GiftItemPO(temp.getString("GoodsID"), temp.getInt("number"));
                    giftList.add(giftItem);
                }
                promotionPO = new PromotionClientPO(resultSet.getString("name"), resultSet.getTimestamp("start"), resultSet.getTimestamp("end"), resultSet.getInt("clientLevel"), resultSet.getDouble("discount"), resultSet.getDouble("voucher"), giftList);
            } else if (type.equals("特价包")) {
                ArrayList<GoodsItemPO> goodsList = new ArrayList<>();
                GoodsItemPO goodsItem;
                sql = "SELECT * FROM GoodsItem WHERE site_ID='" + promotionID + "'";
                ResultSet temp = statement.executeQuery(sql);
                while (temp.next()) {
                    goodsItem = new GoodsItemPO(temp.getString("GoodsID"), temp.getInt("number"), temp.getDouble("price"));
                    goodsList.add(goodsItem);
                }
                promotionPO = new PromotionGoodsPO(resultSet.getString("name"), resultSet.getTimestamp("start"), resultSet.getTimestamp("end"), resultSet.getDouble("discount"), goodsList);
            } else {
                ArrayList<GiftItemPO> giftList = new ArrayList<>();
                GiftItemPO giftItem;
                sql = "SELECT * FROM GiftItem WHERE site_ID='" + promotionID + "'";
                ResultSet temp = statement.executeQuery(sql);
                while (temp.next()) {
                    giftItem = new GiftItemPO(temp.getString("GoodsID"), temp.getInt("number"));
                    giftList.add(giftItem);
                }
                promotionPO = new PromotionTotalPO(resultSet.getString("name"), resultSet.getTimestamp("start"), resultSet.getTimestamp("end"), resultSet.getDouble("total"), resultSet.getDouble("voucher"), giftList);
            }
            return promotionPO;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public synchronized String insert(PromotionPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Promotion WHERE name='" + po.getName() + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();
            sql = "INSERT INTO Promotion (name, type, start, end) VALUE ('" + po.getName() + "','" + po.getType() + "','" + new Timestamp(po.getStart().getTime()) + "','" + new Timestamp(po.getEnd().getTime()) + "') ";
            resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            String ID = null;
            String type = po.getType();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int key = resultSet.getInt(1);
                ID = "Promotion" + String.format("%0" + 8 + "d", key);
                if (type.equals("客户促销策略")) {
                    PromotionClientPO promotionClientPO = (PromotionClientPO) po;
                    sql = "UPDATE Promotion SET ID='" + ID + "', clientLevel='" + promotionClientPO.getClientLevel() + "', discount='" + promotionClientPO.getDiscount() + "', voucher='" + promotionClientPO.getVoucher() + "' WHERE keyID =" + key;
                    statement.executeUpdate(sql);
                    ArrayList<GiftItemPO> list = promotionClientPO.getGiftList();
                    if (list != null)
                        for (int i = 0; i < list.size(); i++) {
                            sql = "INSERT INTO GiftItem VALUE ('" + ID + "','" + list.get(i).goodsID + "','" + list.get(i).number + "')";
                            statement.executeUpdate(sql);
                        }
                } else if (type.equals("特价包")) {
                    PromotionGoodsPO promotionGoodsPO = (PromotionGoodsPO) po;
                    sql = "UPDATE Promotion SET ID='" + ID + "', discount='" + promotionGoodsPO.getDiscount() + "'";
                    statement.executeUpdate(sql);
                    ArrayList<GoodsItemPO> list = new ArrayList<>();
                    if (list != null)
                        for (int i = 0; i < list.size(); i++) {
                            sql = "INSERT INTO GoodsItem VALUES ('" + ID + "', '" + list.get(i).goodsID + "', '" + list.get(i).number + "', '" + list.get(i).price + "')";
                            statement.executeUpdate(sql);
                        }
                } else {
                    PromotionTotalPO promotionTotalPO = (PromotionTotalPO) po;
                    sql = "UPDATE Promotion SET ID='" + ID + "', total='" + promotionTotalPO.getTotal() + "', voucher='" + promotionTotalPO.getVoucher() + "'";
                    statement.executeUpdate(sql);
                    ArrayList<GiftItemPO> list = promotionTotalPO.getGiftList();
                    if (list != null)
                        for (int i = 0; i < list.size(); i++) {
                            sql = "INSERT INTO GiftItem VALUE ('" + ID + "','" + list.get(i).goodsID + "','" + list.get(i).number + "')";
                            statement.executeUpdate(sql);
                        }
                }
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

    @Override
    public synchronized void delete(String promotionID) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Promotion WHERE ID='" + promotionID + "' AND visible=TRUE ";
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

    @Override
    public synchronized void update(PromotionPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Promotion WHERE ID='" + po.getID() + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            sql = "SELECT * FROM Promotion WHERE ID<>'" + po.getID() + "' AND name = '" + po.getName() + "' AND visible = TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();
            String type = po.getType();
            if (type.equals("客户促销策略")) {
                PromotionClientPO promotionClientPO = (PromotionClientPO) po;
                sql = "UPDATE Promotion SET clientLevel='" + promotionClientPO.getClientLevel() + "', discount='" + promotionClientPO.getDiscount() + "', voucher='" + promotionClientPO.getVoucher() + "' WHERE ID ='" + po.getID() + "'";
                statement.executeUpdate(sql);
                sql = "DELETE FROM GiftItem WHERE site_ID='" + po.getID() + "'";
                statement.executeUpdate(sql);
                ArrayList<GiftItemPO> list = promotionClientPO.getGiftList();
                if (list != null)
                    for (int i = 0; i < list.size(); i++) {
                        sql = "INSERT INTO GiftItem VALUE ('" + promotionClientPO.getID() + "','" + list.get(i).goodsID + "','" + list.get(i).number + "')";
                        statement.executeUpdate(sql);
                    }
            } else if (type.equals("特价包")) {
                PromotionGoodsPO promotionGoodsPO = (PromotionGoodsPO) po;
                sql = "UPDATE Promotion SET discount='" + promotionGoodsPO.getDiscount() + "' WHERE site_ID='" + po.getID() + "'";
                statement.executeUpdate(sql);
                sql = "DELETE FROM GoodsItem WHERE site_ID='" + po.getID() + "'";
                statement.executeUpdate(sql);
                ArrayList<GoodsItemPO> list = new ArrayList<>();
                if (list != null)
                    for (int i = 0; i < list.size(); i++) {
                        sql = "INSERT INTO GoodsItem VALUES ('" + promotionGoodsPO.getID() + "', '" + list.get(i).goodsID + "', '" + list.get(i).number + "', '" + list.get(i).price + "')";
                        statement.executeUpdate(sql);
                    }
            } else {
                PromotionTotalPO promotionTotalPO = (PromotionTotalPO) po;
                sql = "UPDATE Promotion SET total='" + promotionTotalPO.getTotal() + "', voucher='" + promotionTotalPO.getVoucher() + "' WHERE site_ID='" + po.getID() + "'";
                statement.executeUpdate(sql);
                sql = "DELETE FROM GiftItem WHERE site_ID='" + po.getID() + "'";
                statement.executeUpdate(sql);
                ArrayList<GiftItemPO> list = promotionTotalPO.getGiftList();
                if (list != null)
                    for (int i = 0; i < list.size(); i++) {
                        sql = "INSERT INTO GiftItem VALUE ('" + promotionTotalPO.getID() + "','" + list.get(i).goodsID + "','" + list.get(i).number + "')";
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
