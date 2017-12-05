package main.java.data.goodsdata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.ExistException;
import main.java.data.datautility.NotExistException;
import main.java.data.datautility.NotNullException;
import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class GoodsData implements GoodsDataService {
    @Override
    public GoodsPO find(String goodsID) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Goods WHERE ID='" + goodsID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            GoodsPO goodsPO = new GoodsPO(resultSet.getString("name"), resultSet.getString("goodsSortID"), resultSet.getString("model"),
                    resultSet.getInt("number"), resultSet.getDouble("cost"), resultSet.getDouble("retail"), resultSet.getDouble("latestCost"),
                    resultSet.getDouble("latestRetail"), resultSet.getInt("alarmNum"), resultSet.getString("comment"));
            goodsPO.setID(resultSet.getString("ID"));
            goodsPO.setVisible(resultSet.getBoolean("visible"));
            resultSet.close();
            statement.close();
            return goodsPO;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    @Override
    public ArrayList<GoodsPO> finds(GoodsQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<GoodsPO> list = new ArrayList<>();
        String sql;
        if (query == null)
            sql = "SELECT * FROM Goods WHERE visible=TRUE ";
        else
            sql = "SELECT * FROM Goods WHERE (name='" + query.name + "' OR goodsSortID='" + query.goodsSortID + "') AND visible=TRUE ";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            GoodsPO goodsPO;
            while (resultSet.next()) {
                goodsPO = new GoodsPO(resultSet.getString("name"), resultSet.getString("goodsSortID"), resultSet.getString("model"),
                        resultSet.getInt("number"), resultSet.getDouble("cost"), resultSet.getDouble("retail"), resultSet.getDouble("latestCost"),
                        resultSet.getDouble("latestRetail"), resultSet.getInt("alarmNum"), resultSet.getString("comment"));
                goodsPO.setID(resultSet.getString("ID"));
                list.add(goodsPO);
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

    @Override
    public synchronized String insert(GoodsPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Goods WHERE name='" + po.getName() + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();
            sql = "SELECT * FROM GoodsSort WHERE ID='" + po.getGoodsSortID() + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            sql = "SELECT * FROM GoodsSort WHERE fatherID='" + po.getGoodsSortID() + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new NotNullException();
            sql = "INSERT INTO Goods (name,goodsSortID,model,number,cost,retail,latestCost,latestRetail,alarmNum,comment) VALUES ('" + po.getName() + "','" + po.getGoodsSortID() + "','" + po.getModel() + "','"
                    + po.getNumber() + "','" + po.getCost() + "','" + po.getRetail() + "','" + po.getLatestCost() + "','" + po.getLatestRetail() + "','" + po.getAlarmNum() + "','" + po.getComment() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            String ID = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int key = resultSet.getInt(1);
                ID = "Goods" + po.getGoodsSortID().split("GoodsSort")[1] + "-" + String.format("%" + 8 + "d", key);
                sql = "UPDATE Goods SET ID='" + ID + "' WHERE keyID=" + key;
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

    @Override
    public synchronized void delete(String goodsID) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Goods WHERE ID='" + goodsID + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            sql = "UPDATE Goods SET visible=FALSE WHERE ID='" + goodsID + "'";
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
    public synchronized void update(GoodsPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Goods WHERE ID='" + po.getID() + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            sql = "SELECT * FROM Goods WHERE ID<>'" + po.getID() + "' AND name='" + po.getName() + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();
            sql = "UPDATE Goods SET name='" + po.getName() + "' WHERE ID='" + po.getID() + "'";
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
}
