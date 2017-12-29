package main.java.data.messagedata;

import main.java.data.DataHelper;
import main.java.exception.DataException;
import main.java.dataservice.messagedataservice.MessageDataService;
import main.java.po.message.MessagePO;

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
public class MessageData extends UnicastRemoteObject implements MessageDataService {
    public MessageData() throws RemoteException {
        MessageDataService messageDataService = this;
        try {
            Naming.rebind("rmi://127.0.0.1:7200/MessageDataService", messageDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param receiverID [接受消息人员的ID]
     * @return 对应receiverID的消息
     * @throws RemoteException,DataException
     */
    @Override
    public ArrayList<MessagePO> finds(String receiverID) throws RemoteException {
        ArrayList<MessagePO> list = new ArrayList<>();
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "SELECT * FROM Message WHERE receiverID='" + receiverID + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            MessagePO messagePO;
            while (resultSet.next()) {
                messagePO = new MessagePO(resultSet.getString("receiverID"), resultSet.getString("senderID"), resultSet.getString("message"), resultSet.getTimestamp("time"));
                list.add(messagePO);
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
     * @param message [消息]
     * @throws RemoteException,DataException
     */
    @Override
    public void insert(MessagePO message) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO Message (receiverID, senderID, message, time) VALUES ('" + message.getReceiverID() + "','" + message.getSenderID() + "','" + message.getMessage() + "','" + new Timestamp(message.getTime().getTime()) + "')";
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

    /**
     * @param receiverID [接收者ID]
     * @param number     [删除消息条数]
     * @throws RemoteException,DataException
     */
    @Override
    public void delete(String receiverID, int number) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM message WHERE receiverID='" + receiverID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            for (int i = 0; i < number; i++) {
                resultSet.next();
                int row = resultSet.getInt("keyID");
                sql = "DELETE FROM message WHERE keyID=" + row;
                connection.createStatement().executeUpdate(sql);
            }
        } catch (SQLException e) {
            try {
                e.printStackTrace();
                connection.rollback();
            } catch (SQLException e1) {
                throw new DataException();
            }
        }
    }
}
