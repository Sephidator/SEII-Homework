package main.java.dataservice.goodsdataservice;

import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GoodsDataService extends Remote {

    ArrayList<GoodsPO> find(GoodsQueryPO query) throws RemoteException;

    String insert(GoodsPO po) throws RemoteException;

    void delete(String goodsID) throws RemoteException;

    void update(GoodsPO po) throws RemoteException;
}
