package main.java.dataservice.goodssortdataservice;

import main.java.po.goods.GoodsSortPO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GoodsSortDataService extends Remote {
    GoodsSortPO find(String goodsSortID) throws RemoteException;

    GoodsSortPO getRoot() throws RemoteException;

    String insert(GoodsSortPO po) throws RemoteException;

    void delete(String GoodsSortID) throws RemoteException;

    void update(GoodsSortPO po) throws RemoteException;
}
