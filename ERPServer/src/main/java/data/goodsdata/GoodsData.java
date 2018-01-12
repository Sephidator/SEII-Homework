package main.java.data.goodsdata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.exception.ExistException;
import main.java.exception.NotExistException;
import main.java.exception.NotNullException;
import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author 陈思彤
 * @description
 * @date 2017/12/04
 */
public class GoodsData extends UnicastRemoteObject implements GoodsDataService {
    public GoodsData() throws RemoteException {
        GoodsDataService goodsDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/GoodsDataService", goodsDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param goodsID [商品ID]
     * @return 对应ID的商品
     * @throws RemoteException,DataException
     */
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

    /**
     * @param query [商品筛选条件]
     * @return 符合筛选条件的商品
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<GoodsPO> finds(GoodsQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<GoodsPO> list = new ArrayList<>();
        String sql;
        if (query == null)
            sql = "SELECT * FROM Goods WHERE visible=TRUE ";
        else
            sql = "SELECT * FROM Goods WHERE (name='" + query.name + "' OR ID='" + query.goodsID + "') AND visible=TRUE ";
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

    /**
     * @param po [商品]
     * @return 新建商品的ID
     * @throws RemoteException,DataException,ExistException,NotExistException,NotNullException
     */
    @Override
    public synchronized String insert(GoodsPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Goods WHERE name='" + po.getName() + "' AND model='" + po.getModel() + "' AND visible=TRUE ";
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
            sql = "INSERT INTO Goods (name, goodsSortID, model, number, cost, retail, latestCost, latestRetail, alarmNum, comment) VALUES ('" + po.getName() + "','" + po.getGoodsSortID() + "','" + po.getModel() + "','"
                    + po.getNumber() + "','" + po.getCost() + "','" + po.getRetail() + "','" + po.getLatestCost() + "','" + po.getLatestRetail() + "','" + po.getAlarmNum() + "','" + po.getComment() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            String ID = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int key = resultSet.getInt(1);
                ID = "Goods" + String.format("%0" + 8 + "d", key);
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

    /**
     * @param goodsID [删除商品ID]
     * @throws RemoteException,DataException,NotExistException,NotNullException
     */
    @Override
    public synchronized void delete(String goodsID) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Goods WHERE ID='" + goodsID + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            int number = resultSet.getInt("number");
            if (number != 0)
                throw new NotNullException();
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

    /**
     * @param po [更新后商品]
     * @throws RemoteException,DataException,NotExistException,ExistException
     */
    @Override
    public synchronized void update(GoodsPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Goods WHERE ID='" + po.getID() + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            sql = "SELECT * FROM GoodsSort WHERE ID='" + po.getGoodsSortID() + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            sql = "SELECT * FROM Goods WHERE ID<>'" + po.getID() + "' AND name='" + po.getName() + "' AND model='" + po.getModel() + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();
            sql = "SELECT * FROM GoodsSort WHERE fatherID='" + po.getGoodsSortID() + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new NotNullException();
            sql = "UPDATE Goods SET name='" + po.getName() + "', goodsSortID='" + po.getGoodsSortID() + "', model='" + po.getModel() + "', number='" + po.getNumber() + "', cost='" + po.getCost() + "', retail='" + po.getRetail() + "', latestCost='" + po.getLatestCost() + "', latestRetail='" + po.getLatestRetail() + "', alarmNum='" + po.getAlarmNum() + "', comment='" + po.getComment() + "' WHERE ID = '" + po.getID() + "'";
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
