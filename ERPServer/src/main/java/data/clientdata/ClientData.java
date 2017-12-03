package main.java.data.clientdata;

import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.data.datautility.NotExistException;
import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.po.account.AccountPO;
import main.java.po.client.ClientPO;
import main.java.po.client.ClientQueryPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class ClientData implements ClientDataService {
    @Override
    public ArrayList<ClientPO> find(ClientQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<ClientPO> list = new ArrayList<>();
        String sql;
        if (query == null)
            sql = "SELECT * FROM Client WHERE visible=TRUE ";
        else if (query.visible)
            sql = "SELECT * FROM Client WHERE (number='" + query.ID + "' OR ID='" + query.ID + "' OR name='" + query.name + "') AND visible=TRUE";
        else
            sql = "SELECT * FROM Client WHERE (number='" + query.ID + "' OR ID='" + query.ID + "' OR name='" + query.name + "')";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ClientPO clientPO;
            while (resultSet.next()) {
                clientPO = new ClientPO(resultSet.getString("category"), resultSet.getInt("level"), resultSet.getString("name"),
                        resultSet.getString("phone"), resultSet.getString("address"), resultSet.getString("post"),
                        resultSet.getString("email"), resultSet.getDouble("receivable"), resultSet.getDouble("payable"),
                        resultSet.getDouble("receivableLimit"), String.format("%0" + 8 + "d", resultSet.getInt("salesmanID")));
                clientPO.setID(resultSet.getString("ID"));
                list.add(clientPO);
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

    @Override
    public synchronized String insert(ClientPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO Client (category, level, name, phone, address, post, email, receivable, payable, " +
                    "receivableLimit, salesmanID) VALUES ('" + po.getCategory() + "'," + po.getLevel() + ",'" + po.getName()
                    + "','" + po.getPhone() + "','" + po.getAddress() + "','" + po.getPost() + "','" + po.getEmail() + "',"
                    + po.getReceivable() + "," + po.getPayable() + "," + po.getReceivableLimit() + "," + po.getSalesmanID() + ")";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            String ID = null;
            if (resultSet.next()) {
                int key = resultSet.getInt(1);
                ID = "Client" + String.format("%0" + 8 + "d", key);
                sql = "UPDATE Client SET ID='" + ID + "' WHERE number=" + key;
                statement.executeUpdate(sql);
            }
            resultSet.close();
            statement.close();
            return ID;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new DataException();
        }
    }

    @Override
    public synchronized void delete(String clientID) throws RemoteException {
        DataHelper.delete(AccountPO.class, clientID);
    }

    @Override
    public synchronized void update(ClientPO po) throws RemoteException {
        Connection connection = DataHelper.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Client WHERE ID='" + po.getID() + "' AND visible=TRUE ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE Client SET category='" + po.getCategory() + "',level=" + po.getLevel() + ",name='" + po.getName() + "',phone='" +
                        po.getPhone() + "',address='" + po.getAddress() + "',post='" + po.getPost() + "',email='" + po.getEmail() + "',receivable=" +
                        po.getReceivable() + ",payable=" + po.getPayable() + ",receivableLimit=" + po.getReceivableLimit() + ",salesmanID=" + po.getSalesmanID();
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
