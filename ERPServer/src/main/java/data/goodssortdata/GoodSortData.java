package main.java.data.goodssortdata;


import main.java.data.DataHelper;
import main.java.data.datautility.DataException;
import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsSortPO;
import main.java.po.goods.GoodsSortQueryPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class GoodSortData implements GoodsSortDataService {
    @Override
    public ArrayList<GoodsSortPO> find(GoodsSortQueryPO query) throws RemoteException {
        Connection connection = DataHelper.getConnection();
        ArrayList<GoodsSortPO> list = new ArrayList<>();
        String sql;
        if (query == null)
            sql = "SELECT * FROM GoodsSort WHERE visible=TRUE";
        else if (query.visible)
            sql = "SELECT  * FROM GoodsSort WHERE ( number='" + query.ID + "' OR ID='" + query.ID + "' OR name='" + query.name + "') AND visible=TRUE ";
        else
            sql = "SELECT  * FROM GoodsSort WHERE ( number='" + query.ID + "' OR ID='" + query.ID + "' OR name='" + query.name + "')";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            GoodsSortPO goodsSortPO;
            while (resultSet.next()) {
                sql = "SELECT * FROM GoodsSort WHERE fatherID='" + resultSet.getString("ID") + "' AND visible=TRUE ";
                ResultSet temp = statement.executeQuery(sql);
                while (temp.next()) {
                    
                }
            }
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
    public String insert(GoodsSortPO po) throws RemoteException {
        return null;
    }

    @Override
    public void delete(String GoodsSortID) throws RemoteException {

    }

    @Override
    public void update(GoodsSortPO po) throws RemoteException {

    }
}
