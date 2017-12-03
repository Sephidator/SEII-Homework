package main.java.data.logdata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.dataservice.logdataservice.LogDataService;
import main.java.po.log.LogPO;
import main.java.po.log.LogQueryPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class LogData implements LogDataService {
    @Override
    public ArrayList<LogPO> find(LogQueryPO query) throws RemoteException {
        ArrayList<LogPO> list = new ArrayList<>();
        Connection connection = DataHelper.getConnection();
        String sql;

        if (query == null)
            sql = "SELECT * FROM Log ";
        else
            sql = "SELECT * FROM Log WHERE time BETWEEN '" + query.start + "' AND '" + query.end + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            LogPO logPO;
            while (resultSet.next()) {
                logPO = new LogPO(String.format("%0" + 8 + "d", resultSet.getInt("operatorID")), resultSet.getString("action"), resultSet.getTimestamp("time"));
                logPO.setID(String.format("%0" + 8 + "d", resultSet.getInt("ID")));
                list.add(logPO);
            }
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    @Override
    public void insert(LogPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO Log (operatorID, action, time) VALUES ('" + po.getOperatorID() + "','" + po.getAction() + "','" + new Timestamp(po.getTime().getTime()) + "')";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }
}
