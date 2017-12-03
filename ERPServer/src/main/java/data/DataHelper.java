package main.java.data;

import main.java.data.datautility.DataException;
import main.java.data.datautility.NotExistException;
import main.java.po.PO;
import main.java.po.account.AccountPO;
import main.java.po.client.ClientPO;
import main.java.po.user.UserPO;

import java.sql.*;

public class DataHelper {
    private String driver = "com.mysql.jdbc.Driver";

    private String url = "jdbc:mysql://localhost:3306/ERP?useUnicode=true&characterEncoding=utf8&useSSL=false";

    private String user = "root";

    private String password = "123456";

    private static Connection connection;

    public void init() {
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

    public static <T extends PO> void delete(Class<T> t, String ID) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String table = null;
            if (t == AccountPO.class)
                table = "Account";
            else if (t == UserPO.class)
                table = "User";
            if (t == ClientPO.class)
                table = "Client";
            String sql = "SELECT * FROM " + table + " WHERE ID='" + ID + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE " + table + " SET visible=FALSE WHERE ID='" + ID + "'";
                statement.executeUpdate(sql);
                resultSet.close();
                statement.close();
            } else
                throw new NotExistException();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }
}
