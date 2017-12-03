package main.java.data.messagedata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.dataservice.messagedataservice.MessageDataService;
import main.java.po.message.MessagePO;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class MessageData implements MessageDataService {
    @Override
    public ArrayList<MessagePO> find(String receiverID) throws RemoteException {
        ArrayList<MessagePO> list = new ArrayList<>();
        Connection connection = DataHelper.getConnection();

        try {
            String sql = "SELECT * FROM Message WHERE receiverID=" + receiverID;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            MessagePO messagePO;
            while (resultSet.next()) {
                messagePO = new MessagePO(String.format("%0" + 8 + "d", resultSet.getInt("receiverID")), String.format("%0" + 8 + "d", resultSet.getInt("senderID")), resultSet.getString("message"));
                list.add(messagePO);
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
    public void insert(MessagePO message) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO Message (receiverID, senderID, message) VALUES (" + message.getReceiverID() + "," + message.getSenderID() + ",'" + message.getMessage() + "')";
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
