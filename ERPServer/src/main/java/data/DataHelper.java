package main.java.data;

import main.java.data.datautility.DataException;
import main.java.data.datautility.NotExistException;
import main.java.po.PO;
import main.java.po.account.AccountPO;
import main.java.po.client.ClientPO;
import main.java.po.user.UserPO;

import java.rmi.RemoteException;
import java.sql.*;

public class DataHelper {
    private static final String driver = "com.mysql.jdbc.Driver";

    private static final String url = "jdbc:mysql://localhost:3306/ERP?useUnicode=true&characterEncoding=utf8&useSSL=false";

    private static final String user = "root";

    private static final String password = "123456";

    private static Connection connection;

    public static void init() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataException();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
