package main.java.data.goodssortdata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.exception.ExistException;
import main.java.exception.NotNullException;
import main.java.exception.NotExistException;
import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsSortPO;

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
public class GoodsSortData extends UnicastRemoteObject implements GoodsSortDataService {
    public GoodsSortData() throws RemoteException {
        GoodsSortDataService goodsSortDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/GoodsSortDataService", goodsSortDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param goodsSortID [商品分类ID]
     * @return 对应ID的商品分类
     * @throws RemoteException,DataException
     */
    @Override
    public GoodsSortPO find(String goodsSortID) throws RemoteException {
        String sql = "SELECT * FROM GoodsSort WHERE ID='" + goodsSortID + "'";

        return getPO(sql);
    }

    /**
     * @return 商品分类的根节点父类
     * @throws RemoteException,DataException
     */
    @Override
    public GoodsSortPO getRoot() throws RemoteException {
        String sql = "SELECT * FROM GoodsSort WHERE visible=TRUE ";

        return getPO(sql);
    }

    /**
     * @param sql
     * @return 商品分类对应的子分类或商品
     * @throws DataException
     */
    private GoodsSortPO getPO(String sql) {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();

            String ID = resultSet.getString("ID");
            Statement tempStatement = connection.createStatement();
            ResultSet tempResultSet;

            sql = "SELECT * FROM GoodsSort WHERE fatherID='" + ID + "' AND visible=TRUE ";
            tempResultSet = tempStatement.executeQuery(sql);

            ArrayList<String> childrenID = new ArrayList<>();
            while (tempResultSet.next()) {
                childrenID.add(tempResultSet.getString("ID"));
            }

            sql = "SELECT * FROM Goods WHERE goodsSortID='" + ID + "' AND visible=TRUE ";
            tempResultSet = tempStatement.executeQuery(sql);

            ArrayList<String> goodsList = new ArrayList<>();
            while (tempResultSet.next()) {
                goodsList.add(tempResultSet.getString("ID"));
            }

            GoodsSortPO goodsSortPO = new GoodsSortPO(resultSet.getString("name"), resultSet.getString("fatherID"), childrenID, goodsList, resultSet.getString("comment"));
            goodsSortPO.setID(resultSet.getString("ID"));
            goodsSortPO.setVisible(resultSet.getBoolean("visible"));

            tempResultSet.close();
            tempStatement.close();
            return goodsSortPO;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    /**
     * @param po [商品分类]
     * @return 新建商品分类ID
     * @throws RemoteException,DataException,ExistException,NotExistException,NotNullException
     */
    @Override
    public synchronized String insert(GoodsSortPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "SELECT * FROM GoodsSort WHERE name='" + po.getName() + "' AND visible=TRUE ";
            Statement statement = connection.createStatement();
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

            sql = "INSERT INTO GoodsSort (name, fatherID, comment) VALUES ('" + po.getName() + "','" + po.getFatherID() + "','" + po.getComment() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);


            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "GoodsSort" + String.format("%0" + 8 + "d", key);

            sql = "UPDATE GoodsSort SET ID='" + ID + "' WHERE keyID=" + key;
            statement.executeUpdate(sql);

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
     * @param GoodsSortID [删除商品分类ID]
     * @throws RemoteException,DataException,NotExistException,NotNullException
     */
    @Override
    public synchronized void delete(String GoodsSortID) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "SELECT * FROM GoodsSort WHERE ID='" + GoodsSortID + "' AND visible=TRUE ";
            Statement statement = connection.createStatement();
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

    /**
     * @param po [更新后商品分类]
     * @throws RemoteException,DataException,NotExistException,ExistException,NotNullException
     */
    @Override
    public synchronized void update(GoodsSortPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "SELECT * FROM GoodsSort WHERE ID='" + po.getID() + "' AND visible=TRUE ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();

            sql = "SELECT * FROM GoodsSort WHERE ID<>'" + po.getID() + "' AND name='" + po.getName() + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();

            sql = "SELECT * FROM Goods WHERE goodsSortID='" + po.getFatherID() + "' AND visible=TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new NotNullException();

            sql = "UPDATE GoodsSort SET name='" + po.getName() + "', fatherID='" + po.getFatherID() + "', comment='" + po.getComment() + "' WHERE ID='" + po.getID() + "'";
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
