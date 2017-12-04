package main.java.data.accountdata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.ExistException;
import main.java.data.datautility.NotExistException;
import main.java.dataservice.accountdataservice.AccountDataService;
import main.java.po.account.AccountPO;
import main.java.po.account.AccountQueryPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class AccountData implements AccountDataService {

    @Override
    public ArrayList<AccountPO> find(AccountQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<AccountPO> list = new ArrayList<>();
        String sql;
        if (query == null)
            sql = "SELECT * FROM Account WHERE visible=TRUE ";
        else if (query.visible)
            sql = "SELECT * FROM Account WHERE (key='" + query.ID + "' OR ID='" + query.ID + "' OR name='" + query.name + "' OR bankAccount='" + query.bankAccount + "') AND visible=TRUE";
        else
            sql = "SELECT * FROM Account WHERE (key='" + query.ID + "' OR ID='" + query.ID + "' OR name='" + query.name + "' OR bankAccount='" + query.bankAccount + "')";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            AccountPO accountPO;
            while (resultSet.next()) {
                accountPO = new AccountPO(resultSet.getString("bankAccount"), resultSet.getString("name"), resultSet.getDouble("remaining"));
                accountPO.setID(resultSet.getString("ID"));
                accountPO.setVisible(resultSet.getBoolean("visible"));
                list.add(accountPO);
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
    public synchronized String insert(AccountPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Account WHERE visible=TRUE AND (bankAccount='" + po.getBankAccount() + "' OR name='" + po.getName() + "')";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                throw new ExistException();
            }
            sql = "INSERT INTO Account (bankAccount, name, remaining) VALUES ('" + po.getBankAccount() + "','" + po.getName() + "','" + po.getRemaining() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            String ID = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int key = resultSet.getInt(1);
                ID = "Account" + String.format("%0" + 8 + "d", key);
                sql = "UPDATE Account SET ID='" + ID + "' WHERE key =" + key;
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
            e.printStackTrace();
            throw new DataException();
        }
    }

    @Override
    public synchronized void delete(String accountID) throws RemoteException {
        DataHelper.delete(AccountPO.class, accountID);
    }

    @Override
    public synchronized void update(AccountPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Account WHERE ID='" + po.getID() + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            sql = "SELECT * FROM Account WHERE ID<>'" + po.getID() + "' AND (bankAccount = '" + po.getBankAccount() + "' OR name = '" + po.getName() + "') AND visible = TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();
            sql = "UPDATE Account SET bankAccount='" + po.getBankAccount() + "', name='" + po.getName() + "', remaining='" + po.getRemaining() + "' WHERE ID='" + po.getID() + "'";
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
