package main.java.data.goodssortdata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.ExistException;
import main.java.data.datautility.NotNullException;
import main.java.data.datautility.NotExistException;
import main.java.data.goodsdata.GoodsData;
import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;
import main.java.po.goods.GoodsSortPO;
import main.java.po.goods.GoodsSortQueryPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class GoodsSortData implements GoodsSortDataService {
    @Override
    public GoodsSortPO find(String goodsSortID) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM GoodsSort WHERE ID='" + goodsSortID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            GoodsSortPO goodsSortPO = getGoodsSortPO(statement, resultSet);
            goodsSortPO.setVisible(resultSet.getBoolean("visible"));
            resultSet.close();
            statement.close();
            return goodsSortPO;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    @Override
    public ArrayList<GoodsSortPO> finds(GoodsSortQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<GoodsSortPO> list = new ArrayList<>();
        String sql;
        if (query == null)
            sql = "SELECT * FROM GoodsSort WHERE visible=TRUE";
        else
            sql = "SELECT  * FROM GoodsSort WHERE name='" + query.name + "' AND visible=TRUE ";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            GoodsSortPO goodsSortPO;
            while (resultSet.next()) {
                goodsSortPO = getGoodsSortPO(statement, resultSet);
                list.add(goodsSortPO);
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

    private GoodsSortPO getGoodsSortPO(Statement statement, ResultSet resultSet) throws SQLException {
        try {
            GoodsSortPO goodsSortPO;
            ArrayList<String> childrenID;
            String ID = resultSet.getString("ID");
            String sql = "SELECT ID FROM GoodsSort WHERE fatherID='" + ID + "' AND visible=TRUE ";
            ResultSet temp = statement.executeQuery(sql);
            childrenID = store(temp);
            ArrayList<String> goodsList = null;
            sql = "SELECT ID FROM Goods WHERE goodsSortID='" + ID + "' AND visible=TRUE ";
            temp = statement.executeQuery(sql);
            goodsList = store(temp);
            goodsSortPO = new GoodsSortPO(resultSet.getString("name"), resultSet.getString("fatherID"), childrenID, goodsList, resultSet.getString("comment"));
            goodsSortPO.setID(ID);
            temp.close();
            return goodsSortPO;
        } catch (SQLException e) {
            throw e;
        }
    }

    private ArrayList<String> store(ResultSet resultSet) throws SQLException {
        ArrayList<String> list = null;
        try {
            if (resultSet.next()) {
                list = new ArrayList<>();
                list.add(resultSet.getString("ID"));
                while (resultSet.next()) {
                    list.add(resultSet.getString("ID"));
                }
            }
            return list;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public synchronized String insert(GoodsSortPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM GoodsSort WHERE name='" + po.getName() + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();
            sql = "SELECT * FROM GoodsSort WHERE ID='" + po.getFatherID() + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            sql = "SELECT * FROM Goods WHERE goodsSortID='" + po.getFatherID() + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new NotNullException();
            sql = "INSERT INTO GoodsSort (name,fatherID,comment) VALUES ('" + po.getName() + "','" + po.getFatherID() + "','" + po.getComment() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            String ID = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int key = resultSet.getInt(1);
                ID = "GoodsSort" + String.format("%" + 8 + "d", key);
                sql = "UPDATE GoodsSort SET ID='" + ID + "' WHERE keyID=" + key;
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
    public synchronized void delete(String GoodsSortID) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM GoodsSort WHERE ID='" + GoodsSortID + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            sql = "SELECT * FROM GoodsSort WHERE fatherID='" + GoodsSortID + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new NotNullException();
            sql = "SELECT * FROM Goods WHERE goodsSortID='" + GoodsSortID + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new NotNullException();
            sql = "UPDATE " + "GoodsSort" + " SET visible=FALSE WHERE ID='" + GoodsSortID + "'";
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
    public synchronized void update(GoodsSortPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM GoodsSort WHERE ID='" + po.getID() + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            sql = "SELECT * FROM GoodsSort WHERE ID<>'" + po.getID() + "' AND name='" + po.getName() + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();
            sql = "UPDATE GoodsSort SET name='" + po.getName() + "' WHERE ID='" + po.getID() + "'";
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
