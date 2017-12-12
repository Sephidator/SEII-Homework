package main.java.data.logdata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.dataservice.logdataservice.LogDataService;
import main.java.po.log.LogPO;
import main.java.po.log.LogQueryPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author 陈思彤
 * @description
 * @date 2017/12/03
 */
public class LogData implements LogDataService {
    /**
     * @param query [日志筛选条件]
     * @return 符合筛选条件的日志
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<LogPO> finds(LogQueryPO query) throws RemoteException {
        ArrayList<LogPO> list = new ArrayList<>();
        Connection connection = DataHelper.getConnection();
        String sql;
        if (query == null)
            sql = "SELECT * FROM Log";
        else {
            sql = "SELECT * FROM Log WHERE time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "'";
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            LogPO logPO;
            while (resultSet.next()) {
                logPO = new LogPO(resultSet.getString("operatorID"), resultSet.getString("action"), resultSet.getTimestamp("time"));
                list.add(logPO);
            }
            resultSet.close();
            statement.close();
            return list;
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
     * @param po [日志]
     * @throws RemoteException,DataException
     */
    @Override
    public void insert(LogPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO Log VALUES ('" + po.getOperatorID() + "','" + po.getAction() + "','" + new Timestamp(po.getTime().getTime()) + "')";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }
}
