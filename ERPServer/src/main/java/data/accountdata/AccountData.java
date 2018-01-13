package main.java.data.accountdata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.exception.ExistException;
import main.java.exception.NotExistException;
import main.java.dataservice.accountdataservice.AccountDataService;
import main.java.exception.NotNullException;
import main.java.po.account.AccountPO;
import main.java.po.account.AccountQueryPO;

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
 * @date 2017/12/03
 */

public class AccountData extends UnicastRemoteObject implements AccountDataService {
    /**
     * @description rmi
     **/
    public AccountData() throws RemoteException {
        AccountDataService accountDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/AccountDataService", accountDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param accountID [帐户ID]
     * @return 对应ID的账户
     * @throws RemoteException,DataException
     */
    @Override
    public AccountPO find(String accountID) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            /**筛选**/
            String sql = "SELECT * FROM Account WHERE ID='" + accountID + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();

            /**构建AccountPO**/
            AccountPO accountPO = new AccountPO(resultSet.getString("bankAccount"), resultSet.getString("name"), resultSet.getDouble("remaining"));
            accountPO.setID(resultSet.getString("ID"));
            accountPO.setVisible(resultSet.getBoolean("visible"));

            resultSet.close();
            statement.close();

            return accountPO;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    /**
     * @param query [账户筛选条件]
     * @return 符合筛选条件的账户
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<AccountPO> finds(AccountQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            /**筛选**/
            String sql = (query == null)
                    ? "SELECT * FROM Account WHERE visible=TRUE "
                    : "SELECT * FROM Account WHERE (name='" + query.name + "' OR bankAccount='" + query.bankAccount + "') AND visible=TRUE";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            /**构建ArrayList<AccountPO>**/
            ArrayList<AccountPO> list = new ArrayList<>();
            while (resultSet.next()) {
                AccountPO accountPO = new AccountPO(resultSet.getString("bankAccount"), resultSet.getString("name"), resultSet.getDouble("remaining"));
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

    /**
     * @param po [账户]
     * @return 新建账户ID
     * @throws RemoteException,DataException,ExistException
     */
    @Override
    public synchronized String insert(AccountPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            /**检查是否有相同的账户（名称或银行账号相同）**/
            String sql = "SELECT * FROM Account WHERE visible=TRUE AND (bankAccount='" + po.getBankAccount() + "' OR name='" + po.getName() + "')";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                throw new ExistException();
            }

            /**插入**/
            sql = "INSERT INTO Account (bankAccount, name, remaining) VALUES ('" + po.getBankAccount() + "','" + po.getName() + "','" + po.getRemaining() + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            /**构造ID**/
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int key = resultSet.getInt(1);
            String ID = "Account" + String.format("%0" + 8 + "d", key);

            /**更新ID**/
            sql = "UPDATE Account SET ID='" + ID + "' WHERE keyID =" + key;
            statement.executeUpdate(sql);

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

    /**
     * @param accountID [删除账户的ID]
     * @throws RemoteException,DataException,NotExistException,NotNullException
     */
    @Override
    public synchronized void delete(String accountID) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "SELECT * FROM Account WHERE ID='" + accountID + "' AND visible=TRUE ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            /**检查账户是否还存在**/
            if (!resultSet.next())
                throw new NotExistException();

            /**检查账户余额是否为0**/
            Double remaining = resultSet.getDouble("remaining");
            if (remaining != 0)
                throw new NotNullException();

            /**删除**/
            sql = "UPDATE Account SET visible=FALSE WHERE ID='" + accountID + "'";
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
     * @param po [更新后的账户]
     * @throws RemoteException,DataException,NotExistException,ExistException
     */
    @Override
    public synchronized void update(AccountPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            /**检查账户是否还存在**/
            String sql = "SELECT * FROM Account WHERE ID='" + po.getID() + "' AND visible=TRUE ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();

            /**检查是否有相同的账户（名称或银行账号相同）**/
            sql = "SELECT * FROM Account WHERE ID<>'" + po.getID() + "' AND (bankAccount = '" + po.getBankAccount() + "' OR name = '" + po.getName() + "') AND visible = TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();

            /**更新**/
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
