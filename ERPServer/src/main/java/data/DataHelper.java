package main.java.data;

import main.java.data.datautility.DataException;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "SELECT * FROM DataHelper";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            if (!dateFormat.format(new Date()).equals(resultSet.getDate("today"))) {
                resultSet = statement.executeQuery("SELECT COUNT (keyID) FROM CashBill");
                int cashBill = resultSet.getInt(1);
                sql = "UPDATE DataHelper SET today='" + new java.sql.Date(new Date().getTime()) + "', cashBill=" + cashBill + "";
                statement.executeUpdate(sql);
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
