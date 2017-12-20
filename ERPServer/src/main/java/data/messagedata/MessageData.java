package main.java.data.messagedata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.dataservice.messagedataservice.MessageDataService;
import main.java.po.message.MessagePO;

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
                messagePO = new MessagePO(resultSet.getString("receiverID"), resultSet.getString("senderID"), resultSet.getString("message"));
                list.add(messagePO);
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
     * @param message [消息]
     * @throws RemoteException,DataException
     */
    @Override
    public void insert(MessagePO message) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO Message VALUES ('" + message.getReceiverID() + "','" + message.getSenderID() + "','" + message.getMessage() + "')";
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
