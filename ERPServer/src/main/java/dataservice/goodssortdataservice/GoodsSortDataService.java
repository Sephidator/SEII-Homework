package main.java.dataservice.goodssortdataservice;

import main.java.po.goods.GoodsSortPO;
import main.java.po.goods.GoodsSortQueryPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GoodsSortDataService extends Remote {

    ArrayList<GoodsSortPO> find(GoodsSortQueryPO query) throws RemoteException;

    String insert(GoodsSortPO po) throws RemoteException;

    void delete(String GoodsSortID) throws RemoteException;

    void update(GoodsSortPO po) throws RemoteException;
}
