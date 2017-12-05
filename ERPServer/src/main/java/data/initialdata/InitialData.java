package main.java.data.initialdata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.dataservice.initialdataservice.InitialDataService;
import main.java.po.account.AccountPO;
import main.java.po.client.ClientPO;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsSortPO;
import main.java.po.initial.InitialPO;
import main.java.po.initial.InitialQueryPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class InitialData implements InitialDataService {
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
            while (resultSet.next()) {
                String initialID = resultSet.getString("ID");
                sql = "SELECT * FROM GoodsRecord WHERE InitialID='" + initialID + "'";
                tempResultSet = statement.executeQuery(sql);
                while (tempResultSet.next()) {
                    goodsPO = new GoodsPO(tempResultSet.getString("name"), tempResultSet.getString("goodsSortID"), tempResultSet.getString("model"),
                            tempResultSet.getInt("number"), tempResultSet.getDouble("cost"), tempResultSet.getDouble("retail"), tempResultSet.getDouble("latestCost"),
                            tempResultSet.getDouble("latestRetail"), tempResultSet.getInt("alarmNum"), tempResultSet.getString("comment"));
                    goodsPO.setID(tempResultSet.getString("ID"));
                    goodsPOS.add(goodsPO);
                }
                sql = "SELECT * FROM GoodsSortRecord WHERE InitialID='" + initialID + "'";
                tempResultSet = statement.executeQuery(sql);
                while (tempResultSet.next()) {
                    String ID = tempResultSet.getString("ID");
                    ArrayList<String> childrenID;
                    sql = "SELECT ID FROM GoodsSortRecord WHERE fatherID='" + ID + "'";
                    ResultSet tempResultSetOfGoodsSort = statement.executeQuery(sql);
                    childrenID = store(tempResultSetOfGoodsSort);
                    ArrayList<String> goodsList;
                    sql = "SELECT ID FROM GoodsRecord WHERE goodsSortID='" + ID + "'";
                    tempResultSetOfGoodsSort = statement.executeQuery(sql);
                    goodsList = store(tempResultSetOfGoodsSort);
                    goodsSortPO = new GoodsSortPO(tempResultSet.getString("name"), tempResultSet.getString("fatherID"), childrenID, goodsList, tempResultSet.getString("comment"));
                    goodsSortPO.setID(ID);
                    goodsSortPOS.add(goodsSortPO);
                }
                sql = "SELECT * FROM ClientRecord WHERE InitialID='" + initialID + "'";
                tempResultSet = statement.executeQuery(sql);
                while (tempResultSet.next()) {
                    clientPO = new ClientPO(tempResultSet.getString("category"), tempResultSet.getInt("level"), tempResultSet.getString("name"),
                            tempResultSet.getString("phone"), tempResultSet.getString("address"), tempResultSet.getString("post"),
                            tempResultSet.getString("email"), tempResultSet.getDouble("receivable"), tempResultSet.getDouble("payable"),
                            tempResultSet.getDouble("receivableLimit"), tempResultSet.getString("salesmanID"));
                    clientPO.setID(resultSet.getString("ID"));
                    clientPOS.add(clientPO);
                }
                sql = "SELECT * FROM AccountRecord WHERE InitialID='" + initialID + "'";
                tempResultSet = statement.executeQuery(sql);
                while (tempResultSet.next()) {
                    accountPO = new AccountPO(tempResultSet.getString("bankAccount"), tempResultSet.getString("name"), tempResultSet.getDouble("remaining"));
                    accountPO.setID(resultSet.getString("ID"));
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

    private ArrayList<String> store(ResultSet resultSet) throws SQLException {
        ArrayList<String> list = null;
        try {
            if (resultSet.next()) {
                list = new ArrayList<>();
                list.add(resultSet.getString("ID"));
                while (resultSet.next())
                    list.add(resultSet.getString("ID"));
            }
            return list;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public String insert(InitialPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO Initial (year) VALUE " + po.getYear();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            String ID = null;
            if (resultSet.next()) {
                int key = resultSet.getInt(1);
                ID = "Initial" + String.format("%0" + 8 + "d", key);
                sql = "UPDATE Initial SET ID='" + ID + "' WHERE keyID =" + key;
                statement.executeUpdate(sql);
            }
            ArrayList<GoodsPO> goodsPOS = po.getGoodsList();
            for (int i = 0; i < goodsPOS.size(); i++) {
                GoodsPO goodsPO = goodsPOS.get(i);
                sql = "INSERT INTO GoodsRecord (ID,name,goodsSortID,model,number,cost,retail,latestCost,latestRetail,alarmNum,comment) VALUES ('" + goodsPO.getName() + "','" +
                        goodsPO.getGoodsSortID() + "','" + goodsPO.getModel() + "','" + goodsPO.getNumber() + "','" + goodsPO.getCost() + "','" + goodsPO.getRetail() + "','" +
                        goodsPO.getLatestCost() + "','" + goodsPO.getLatestRetail() + "','" + goodsPO.getAlarmNum() + "','" + goodsPO.getComment() + "')";
                statement.executeUpdate(sql);
            }
            ArrayList<GoodsSortPO> goodsSortPOS = po.getGoodsSortList();
            for (int i = 0; i < goodsSortPOS.size(); i++) {
                GoodsSortPO goodsSortPO = goodsSortPOS.get(i);
                sql = "INSERT INTO GoodsSortRecord (ID, name, fatherID, comment) VALUES ('" + goodsSortPO.getID() + "','" + goodsSortPO.getName() + "','" + goodsSortPO.getFatherID() + "','" + goodsSortPO.getComment() + "')";
                statement.executeUpdate(sql);
            }
            ArrayList<ClientPO> clientPOS = po.getClientList();
            for (int i = 0; i < clientPOS.size(); i++) {
                ClientPO clientPO = clientPOS.get(i);
                sql = "INSERT INTO ClientRecord (ID, category, level, name, phone, address, post, email, receivable, payable, " +
                        "receivableLimit, salesmanID) VALUES ('" + clientPO.getID() + "','" + clientPO.getCategory() + "'," + clientPO.getLevel() + ",'" + clientPO.getName()
                        + "','" + clientPO.getPhone() + "','" + clientPO.getAddress() + "','" + clientPO.getPost() + "','" + clientPO.getEmail() + "',"
                        + clientPO.getReceivable() + "," + clientPO.getPayable() + "," + clientPO.getReceivableLimit() + "," + clientPO.getSalesmanID() + ")";
                statement.executeUpdate(sql);
            }
            ArrayList<AccountPO> accountPOS = po.getAccountList();
            for (int i = 0; i < accountPOS.size(); i++) {
                AccountPO accountPO = accountPOS.get(i);
                sql = "INSERT INTO AccountRecord (ID, bankAccount, name, remaining) VALUES ('" + accountPO.getID() + "',''" + accountPO.getBankAccount() + "','" + accountPO.getName() + "','" + accountPO.getRemaining() + "')";
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
}
