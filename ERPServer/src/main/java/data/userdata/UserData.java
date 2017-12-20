package main.java.data.userdata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.ExistException;
import main.java.data.datautility.LoginException;
import main.java.data.datautility.NotExistException;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;

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
public class UserData extends UnicastRemoteObject implements UserDataService {
    public UserData() throws RemoteException {
        UserDataService userDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/UserDataService", userDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param userID [帐号ID]
     * @return 对应ID的帐号
     * @throws RemoteException,DataException
     */
    @Override
    public UserPO find(String userID) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM User WHERE ID='" + userID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            UserPO userPO = new UserPO(resultSet.getString("name"), resultSet.getString("type"), resultSet.getString("jobName"), resultSet.getString("password"), resultSet.getInt("age"), resultSet.getBoolean("top"));
            userPO.setID(resultSet.getString("ID"));
            userPO.setVisible(resultSet.getBoolean("visible"));
            userPO.setLogin(resultSet.getBoolean("login"));
            return userPO;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    /**
     * @param query [帐号筛选条件]
     * @return 符合筛选条件的帐号
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<UserPO> finds(UserQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<UserPO> list = new ArrayList<>();
        String sql;

        if (query == null)
            sql = "SELECT * FROM User WHERE visible=TRUE ";
        else
            sql = "SELECT * FROM User WHERE (name='" + query.name + "' OR type='" + query.type + "') AND visible=TRUE ";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            UserPO userPO;
            while (resultSet.next()) {
                userPO = new UserPO(resultSet.getString("name"), resultSet.getString("type"), resultSet.getString("jobName"), resultSet.getString("password"), resultSet.getInt("age"), resultSet.getBoolean("top"));
                userPO.setID(resultSet.getString("ID"));
                userPO.setLogin(resultSet.getBoolean("login"));
                list.add(userPO);
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
     * @param po [帐号]
     * @return 新建帐号ID
     * @throws RemoteException,DataException,ExistException
     */
    @Override
    public synchronized String insert(UserPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM User WHERE visible=TRUE AND jobName='" + po.getJobName() + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                throw new ExistException();
            }
            sql = "INSERT INTO User (name, type, jobName, password, age, top) VALUES ('" + po.getName() + "','" + po.getType() + "','" + po.getJobName() + "','" + po.getPassword() + "','" + po.getAge() + "'," + po.isTop() + ")";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            String ID = null;
            if (resultSet.next()) {
                int key = resultSet.getInt(1);
                ID = "User" + String.format("%0" + 8 + "d", key);
                sql = "UPDATE User SET ID='" + ID + "' WHERE keyID='" + key + "'";
                statement.executeUpdate(sql);
            }
            resultSet.close();
            statement.close();
            return ID;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    /**
     * @param userID [删除帐号ID]
     * @throws RemoteException,DataException,NotExistException
     */
    @Override
    public synchronized void delete(String userID) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM User WHERE ID='" + userID + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            sql = "UPDATE User SET visible=FALSE WHERE ID='" + userID + "'";
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
     * @param po [更新后帐号]
     * @throws RemoteException,DataException,NotExistException,ExistException
     */
    @Override
    public synchronized void update(UserPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM User WHERE ID='" + po.getID() + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            sql = "SELECT * FROM User WHERE ID<>'" + po.getID() + "' AND jobName= '" + po.getJobName() + "' AND visible = TRUE ";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                throw new ExistException();
            sql = "UPDATE User SET name='" + po.getName() + "', type='" + po.getType() + "', jobName='" + po.getJobName() + "', password='" + po.getPassword() + "', age='" + po.getAge() + "', top=" + po.isTop() + " WHERE ID='" + po.getID() + "'";
            statement.executeUpdate(sql);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e1) {
            }
            e.printStackTrace();
            throw new DataException();
        }
    }

    /**
     * @param jobName  [工号]
     * @param password [密码]
     * @return 对应工号和密码的帐号
     * @throws RemoteException,DataException,NotExistException,LoginException
     * @description 提供登录服务，不支持多地登录
     */
    @Override
    public synchronized UserPO login(String jobName, String password) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM User WHERE jobName='" + jobName + "' AND password='" + password + "' AND visible=TRUE";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new NotExistException();
            if (resultSet.getBoolean("login"))
                throw new LoginException();
            UserPO userPO = new UserPO(resultSet.getString("name"), resultSet.getString("type"), resultSet.getString("jobName"), resultSet.getString("password"), resultSet.getInt("age"), resultSet.getBoolean("top"));
            String ID = resultSet.getString("ID");
            userPO.setID(ID);
            userPO.setLogin(true);
            sql = "UPDATE User SET login=TRUE WHERE ID='" + ID + "'";
            statement.executeUpdate(sql);
            resultSet.close();
            statement.close();
            return userPO;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    /**
     * @param UserID [登出帐号ID]
     * @throws RemoteException,DataException
     * @@description 提供登出服务
     */
    @Override
    public synchronized void logout(String UserID) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM User WHERE ID='" + UserID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            sql = "UPDATE User SET login=FALSE WHERE ID='" + UserID + "'";
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
