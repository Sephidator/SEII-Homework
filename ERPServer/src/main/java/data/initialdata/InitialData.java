package main.java.data.initialdata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.exception.ExistException;
import main.java.dataservice.initialdataservice.InitialDataService;
import main.java.po.account.AccountPO;
import main.java.po.client.ClientPO;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsSortPO;
import main.java.po.initial.InitialPO;
import main.java.po.initial.InitialQueryPO;

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
public class InitialData extends UnicastRemoteObject implements InitialDataService {
    public InitialData() throws RemoteException {
        InitialDataService initialDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/InitialDataService", initialDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param query [期初建帐]
     * @return 符合筛选条件的期初建帐
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<InitialPO> finds(InitialQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<InitialPO> list = new ArrayList<>();
        String sql;
        if (query == null)
            sql = "SELECT * FROM Initial WHERE year BETWEEN " + query.start + " AND " + query.end;
        else
            sql = "SELECT * FROM Initial";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ArrayList<GoodsPO> goodsPOS = new ArrayList<>();
            GoodsPO goodsPO;
            ArrayList<GoodsSortPO> goodsSortPOS = new ArrayList<>();
            GoodsSortPO goodsSortPO;
            ArrayList<ClientPO> clientPOS = new ArrayList<>();
            ClientPO clientPO;
            ArrayList<AccountPO> accountPOS = new ArrayList<>();
            AccountPO accountPO;
            ResultSet tempResultSet;
            Statement tempStatement = connection.createStatement();
            while (resultSet.next()) {
                String initialID = resultSet.getString("ID");
                sql = "SELECT * FROM GoodsRecord WHERE InitialID='" + initialID + "'";
                tempResultSet = tempStatement.executeQuery(sql);
                while (tempResultSet.next()) {
                    goodsPO = new GoodsPO(tempResultSet.getString("name"), tempResultSet.getString("goodsSortID"), tempResultSet.getString("model"),
                            tempResultSet.getInt("number"), tempResultSet.getDouble("cost"), tempResultSet.getDouble("retail"), tempResultSet.getDouble("latestCost"),
                            tempResultSet.getDouble("latestRetail"), tempResultSet.getInt("alarmNum"), tempResultSet.getString("comment"));
                    goodsPO.setID(tempResultSet.getString("ID"));
                    goodsPOS.add(goodsPO);
                }
                sql = "SELECT * FROM GoodsSortRecord WHERE InitialID='" + initialID + "'";
                tempResultSet = tempStatement.executeQuery(sql);
                while (tempResultSet.next()) {
                    String ID = tempResultSet.getString("ID");
                    ArrayList<String> childrenID;
                    sql = "SELECT ID FROM GoodsSortRecord WHERE fatherID='" + ID + "'";
                    Statement tempStatementOfGoodsSort = connection.createStatement();
                    ResultSet tempResultSetOfGoodsSort = tempStatementOfGoodsSort.executeQuery(sql);
                    childrenID = store(tempResultSetOfGoodsSort);
                    ArrayList<String> goodsList;
                    sql = "SELECT ID FROM GoodsRecord WHERE goodsSortID='" + ID + "'";
                    tempResultSetOfGoodsSort = tempStatementOfGoodsSort.executeQuery(sql);
                    goodsList = store(tempResultSetOfGoodsSort);
                    goodsSortPO = new GoodsSortPO(tempResultSet.getString("name"), tempResultSet.getString("fatherID"), childrenID, goodsList, tempResultSet.getString("comment"));
                    goodsSortPO.setID(ID);
                    goodsSortPOS.add(goodsSortPO);
                }
                sql = "SELECT * FROM ClientRecord WHERE InitialID='" + initialID + "'";
                tempResultSet = tempStatement.executeQuery(sql);
                while (tempResultSet.next()) {
                    clientPO = new ClientPO(tempResultSet.getString("category"), tempResultSet.getInt("level"), tempResultSet.getString("name"),
                            tempResultSet.getString("phone"), tempResultSet.getString("address"), tempResultSet.getString("post"),
                            tempResultSet.getString("email"), tempResultSet.getDouble("receivable"), tempResultSet.getDouble("payable"),
                            tempResultSet.getDouble("receivableLimit"), tempResultSet.getString("salesmanID"));
                    clientPO.setID(tempResultSet.getString("ID"));
                    clientPOS.add(clientPO);
                }
                sql = "SELECT * FROM AccountRecord WHERE InitialID='" + initialID + "'";
                tempResultSet = tempStatement.executeQuery(sql);
                while (tempResultSet.next()) {
                    accountPO = new AccountPO(tempResultSet.getString("bankAccount"), tempResultSet.getString("name"), tempResultSet.getDouble("remaining"));
                    accountPO.setID(tempResultSet.getString("ID"));
                    accountPOS.add(accountPO);
                }
                list.add(new InitialPO(resultSet.getInt("year"), goodsPOS, goodsSortPOS, clientPOS, accountPOS));
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
        return null;
    }

    /**
     * @param resultSet
     * @return 商品分类的子分类或商品
     * @throws SQLException
     */
    private ArrayList<String> store(ResultSet resultSet) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        while (resultSet.next())
            list.add(resultSet.getString("ID"));
        return list;
    }

    /**
     * @param po [期初建帐]
     * @return 新建期初建帐的ID
     * @throws RemoteException,DataException
     */
    @Override
    public synchronized String insert(InitialPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Initial WHERE year=" + po.getYear();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();
            sql = "INSERT INTO Initial (year) VALUE (" + po.getYear() + ")";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            String ID = null;
            if (resultSet.next()) {
                int key = resultSet.getInt(1);
                ID = "Initial" + String.format("%0" + 8 + "d", key);
                sql = "UPDATE Initial SET ID='" + ID + "' WHERE keyID =" + key;
                statement.executeUpdate(sql);
            }
            ArrayList<GoodsPO> goodsPOS = po.getGoodsList();
            if (goodsPOS != null)
                for (int i = 0; i < goodsPOS.size(); i++) {
                    GoodsPO goodsPO = goodsPOS.get(i);
                    sql = "INSERT INTO GoodsRecord VALUES ('" + ID + "','" + goodsPO.getName() + "','" +
                            goodsPO.getGoodsSortID() + "','" + goodsPO.getModel() + "','" + goodsPO.getNumber() + "','" + goodsPO.getCost() + "','" + goodsPO.getRetail() + "','" +
                            goodsPO.getLatestCost() + "','" + goodsPO.getLatestRetail() + "','" + goodsPO.getAlarmNum() + "','" + goodsPO.getComment() + "')";
                    statement.executeUpdate(sql);
                }
            ArrayList<GoodsSortPO> goodsSortPOS = po.getGoodsSortList();
            if (goodsSortPOS != null)
                for (int i = 0; i < goodsSortPOS.size(); i++) {
                    GoodsSortPO goodsSortPO = goodsSortPOS.get(i);
                    sql = "INSERT INTO GoodsSortRecord VALUES ('" + ID + "','" + goodsSortPO.getID() + "','" + goodsSortPO.getName() + "','" + goodsSortPO.getFatherID() + "','" + goodsSortPO.getComment() + "')";
                    statement.executeUpdate(sql);
                }
            ArrayList<ClientPO> clientPOS = po.getClientList();
            if (clientPOS != null)
                for (int i = 0; i < clientPOS.size(); i++) {
                    ClientPO clientPO = clientPOS.get(i);
                    sql = "INSERT INTO ClientRecord VALUES ('" + ID + "','" + clientPO.getID() + "','" + clientPO.getCategory() + "'," + clientPO.getLevel() + ",'" + clientPO.getName()
                            + "','" + clientPO.getPhone() + "','" + clientPO.getAddress() + "','" + clientPO.getPost() + "','" + clientPO.getEmail() + "',"
                            + clientPO.getReceivable() + "," + clientPO.getPayable() + "," + clientPO.getReceivableLimit() + ",'" + clientPO.getSalesmanID() + "')";
                    statement.executeUpdate(sql);
                }
            ArrayList<AccountPO> accountPOS = po.getAccountList();
            if (accountPOS != null)
                for (int i = 0; i < accountPOS.size(); i++) {
                    AccountPO accountPO = accountPOS.get(i);
                    sql = "INSERT INTO AccountRecord VALUES ('" + ID + "','" + accountPO.getID() + "','" + accountPO.getBankAccount() + "','" + accountPO.getName() + "','" + accountPO.getRemaining() + "')";
                    statement.executeUpdate(sql);
                }
            return ID;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            e.printStackTrace();
            throw new DataException();
        }
    }
}
