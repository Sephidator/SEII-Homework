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

        try {
            String sql = (query == null)
                    ? "SELECT * FROM Initial"
                    : "SELECT * FROM Initial WHERE year =" + query.year;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            ArrayList<InitialPO> list = new ArrayList<>();
            while (resultSet.next()) {
                String initialID = resultSet.getString("ID");
                Statement tempStatement = connection.createStatement();
                ResultSet tempResult;

                sql = "SELECT * FROM GoodsRecord WHERE InitialID='" + initialID + "'";
                tempResult = tempStatement.executeQuery(sql);

                ArrayList<GoodsPO> goodsPOS = new ArrayList<>();
                while (tempResult.next()) {
                    GoodsPO goodsPO = new GoodsPO(tempResult.getString("name"), tempResult.getString("goodsSortID"), tempResult.getString("model"),
                            tempResult.getInt("number"), tempResult.getDouble("cost"), tempResult.getDouble("retail"), tempResult.getDouble("latestCost"),
                            tempResult.getDouble("latestRetail"), tempResult.getInt("alarmNum"), tempResult.getString("comment"));
                    goodsPO.setID(tempResult.getString("ID"));
                    goodsPOS.add(goodsPO);
                }

                sql = "SELECT * FROM ClientRecord WHERE InitialID='" + initialID + "'";
                tempResult = tempStatement.executeQuery(sql);

                ArrayList<ClientPO> clientPOS = new ArrayList<>();
                while (tempResult.next()) {
                    ClientPO clientPO = new ClientPO(tempResult.getString("category"), tempResult.getInt("level"), tempResult.getString("name"),
                            tempResult.getString("phone"), tempResult.getString("address"), tempResult.getString("post"),
                            tempResult.getString("email"), tempResult.getDouble("receivable"), tempResult.getDouble("payable"),
                            tempResult.getDouble("receivableLimit"), tempResult.getString("salesmanID"));
                    clientPO.setID(tempResult.getString("ID"));
                    clientPOS.add(clientPO);
                }

                sql = "SELECT * FROM AccountRecord WHERE InitialID='" + initialID + "'";
                tempResult = tempStatement.executeQuery(sql);

                ArrayList<AccountPO> accountPOS = new ArrayList<>();
                while (tempResult.next()) {
                    AccountPO accountPO = new AccountPO(tempResult.getString("bankAccount"), tempResult.getString("name"), tempResult.getDouble("remaining"));
                    accountPO.setID(tempResult.getString("ID"));
                    accountPOS.add(accountPO);
                }

                InitialPO initialPO = new InitialPO(resultSet.getInt("year"), goodsPOS, clientPOS, accountPOS);
                initialPO.setID(initialID);
                list.add(initialPO);
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
     * @param po [期初建帐]
     * @return 新建期初建帐的ID
     * @throws RemoteException,DataException,ExistException
     */
    @Override
    public synchronized String insert(InitialPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "SELECT * FROM Initial WHERE year=" + po.getYear();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();

            sql = "INSERT INTO Initial (year) VALUE (" + po.getYear() + ")";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "Initial" + String.format("%0" + 8 + "d", key);

            sql = "UPDATE Initial SET ID='" + ID + "' WHERE keyID =" + key;
            statement.executeUpdate(sql);

            ArrayList<GoodsPO> goodsPOS = po.getGoodsList();
            for (GoodsPO goodsPO : goodsPOS) {
                sql = "INSERT INTO GoodsRecord VALUES ('" + ID + "','" + goodsPO.getID() + "','" + goodsPO.getName() + "','" +
                        goodsPO.getGoodsSortID() + "','" + goodsPO.getModel() + "','" + goodsPO.getNumber() + "','" + goodsPO.getCost() + "','" + goodsPO.getRetail() + "','" +
                        goodsPO.getLatestCost() + "','" + goodsPO.getLatestRetail() + "','" + goodsPO.getAlarmNum() + "','" + goodsPO.getComment() + "')";
                statement.executeUpdate(sql);
            }

            ArrayList<ClientPO> clientPOS = po.getClientList();
            for (ClientPO clientPO : clientPOS) {
                sql = "INSERT INTO ClientRecord VALUES ('" + ID + "','" + clientPO.getID() + "','" + clientPO.getCategory() + "'," + clientPO.getLevel() + ",'" + clientPO.getName()
                        + "','" + clientPO.getPhone() + "','" + clientPO.getAddress() + "','" + clientPO.getPost() + "','" + clientPO.getEmail() + "',"
                        + clientPO.getReceivable() + "," + clientPO.getPayable() + "," + clientPO.getReceivableLimit() + ",'" + clientPO.getSalesmanID() + "')";
                statement.executeUpdate(sql);
            }

            ArrayList<AccountPO> accountPOS = po.getAccountList();
            for (AccountPO accountPO : accountPOS) {
                sql = "INSERT INTO AccountRecord VALUES ('" + ID + "','" + accountPO.getID() + "','" + accountPO.getBankAccount() + "','" + accountPO.getName() + "','" + accountPO.getRemaining() + "')";
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
