package main.java.data.initialdata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.exception.ExistException;
import main.java.dataservice.initialdataservice.InitialDataService;
import main.java.po.account.AccountPO;
import main.java.po.client.ClientPO;
import main.java.po.goods.GoodsPO;
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
            ArrayList<ClientPO> clientPOS = new ArrayList<>();
            ClientPO clientPO;
            ArrayList<AccountPO> accountPOS = new ArrayList<>();
            AccountPO accountPO;
            while (resultSet.next()) {
                String initialID = resultSet.getString("ID");
                int year = resultSet.getInt("year");
                sql = "SELECT * FROM GoodsRecord WHERE InitialID='" + initialID + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    goodsPO = new GoodsPO(resultSet.getString("name"), resultSet.getString("goodsSortID"), resultSet.getString("model"),
                            resultSet.getInt("number"), resultSet.getDouble("cost"), resultSet.getDouble("retail"), resultSet.getDouble("latestCost"),
                            resultSet.getDouble("latestRetail"), resultSet.getInt("alarmNum"), resultSet.getString("comment"));
                    goodsPO.setID(resultSet.getString("ID"));
                    goodsPOS.add(goodsPO);
                }
                sql = "SELECT * FROM ClientRecord WHERE InitialID='" + initialID + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    clientPO = new ClientPO(resultSet.getString("category"), resultSet.getInt("level"), resultSet.getString("name"),
                            resultSet.getString("phone"), resultSet.getString("address"), resultSet.getString("post"),
                            resultSet.getString("email"), resultSet.getDouble("receivable"), resultSet.getDouble("payable"),
                            resultSet.getDouble("receivableLimit"), resultSet.getString("salesmanID"));
                    clientPO.setID(resultSet.getString("ID"));
                    clientPOS.add(clientPO);
                }
                sql = "SELECT * FROM AccountRecord WHERE InitialID='" + initialID + "'";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    accountPO = new AccountPO(resultSet.getString("bankAccount"), resultSet.getString("name"), resultSet.getDouble("remaining"));
                    accountPO.setID(resultSet.getString("ID"));
                    accountPOS.add(accountPO);
                }
                list.add(new InitialPO(year, goodsPOS, clientPOS, accountPOS));
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
