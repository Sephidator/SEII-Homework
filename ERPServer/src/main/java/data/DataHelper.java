package main.java.data;

import main.java.data.datautility.DataException;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 陈思彤
 * @description 数据库相关操作
 * @date 2017/12/03
 */
public class DataHelper {
    private static final String driver = "com.mysql.jdbc.Driver";

    private static final String url = "jdbc:mysql://localhost:3306/ERP?useUnicode=true&characterEncoding=utf8&useSSL=false";

    private static final String user = "root";

    private static final String password = "123456";

    private static Connection connection;

    /**
     * @description 数据库连接，更新数据库数据
     */
    public static void init() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "SELECT * FROM DataHelper";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                sql = "INSERT INTO DataHelper VALUES ('" + new java.sql.Date(new Date().getTime()) + "', 0, 0, 0, 0, 0, 0, 0, 0, 0)";
                statement.executeUpdate(sql);
            } else if (!dateFormat.format(new Date()).equals(resultSet.getString("today"))) {
                resultSet = statement.executeQuery("SELECT COUNT(*) FROM CashBill");
                resultSet.next();
                int cashBill = resultSet.getInt(1);
                resultSet = statement.executeQuery("SELECT COUNT(*) FROM PaymentBill");
                resultSet.next();
                int paymentBill = resultSet.getInt(1);
                resultSet = statement.executeQuery("SELECT COUNT(*) FROM ReceiptBill");
                resultSet.next();
                int receiptBill = resultSet.getInt(1);
                resultSet = statement.executeQuery("SELECT COUNT(*) FROM InventoryGiftBill");
                resultSet.next();
                int inventoryGiftBill = resultSet.getInt(1);
                resultSet = statement.executeQuery("SELECT COUNT(*) FROM InventoryLossOverBill");
                resultSet.next();
                int inventoryLossOverBill = resultSet.getInt(1);
                resultSet = statement.executeQuery("SELECT COUNT(*) FROM PurchaseRefundBill");
                resultSet.next();
                int purchaseRefundBill = resultSet.getInt(1);
                resultSet = statement.executeQuery("SELECT COUNT(*) FROM SaleRefundBill");
                resultSet.next();
                int saleRefundBill = resultSet.getInt(1);
                sql = "UPDATE DataHelper SET today='" + new java.sql.Date(new Date().getTime()) + "', CashBill=" + cashBill + ", PaymentBill=" + paymentBill + ", ReceiptBill=" + receiptBill + ", InventoryGiftBill=" + inventoryGiftBill + ", InventoryLossOverBill=" + inventoryLossOverBill + ", PurchaseRefundBill=" + purchaseRefundBill + ", SaleRefundBill=" + saleRefundBill;
                statement.executeUpdate(sql);
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    /**
     * @return 数据库连接的Connection
     */
    public static Connection getConnection() {
        return connection;
    }
}
