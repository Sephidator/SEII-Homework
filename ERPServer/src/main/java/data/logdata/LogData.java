package main.java.data.logdata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.dataservice.logdataservice.LogDataService;
import main.java.po.log.LogPO;
import main.java.po.log.LogQueryPO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author 陈思彤
 * @description
 * @date 2017/12/03
 */
public class LogData extends UnicastRemoteObject implements LogDataService {

    public LogData() throws RemoteException {
        LogDataService logDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/LogDataService", logDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param query [日志筛选条件]
     * @return 符合筛选条件的日志
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<LogPO> finds(LogQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = (query == null)
                    ? "SELECT * FROM Log"
                    : "SELECT * FROM Log WHERE time BETWEEN '" + new Timestamp(query.start.getTime()) + "' AND '" + new Timestamp(query.end.getTime()) + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            ArrayList<LogPO> list = new ArrayList<>();
            while (resultSet.next()) {
                LogPO logPO = new LogPO(resultSet.getString("operatorID"), resultSet.getString("action"), resultSet.getTimestamp("time"));
                list.add(logPO);
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
     * @param po [日志]
     * @throws RemoteException,DataException
     */
    @Override
    public void insert(LogPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "INSERT INTO Log VALUES ('" + po.getOperatorID() + "','" + po.getAction() + "','" + new Timestamp(po.getTime().getTime()) + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

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
