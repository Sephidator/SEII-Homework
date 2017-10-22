package dataservice.goodsdataservice;

import businesslogic.blutility.ResultMessage;
import po.GoodsPO;
import java.rmi.RemoteException;

public interface GoodsDataService {


    public GoodsPO findGoods(String ID, String name) throws RemoteException;

    public ResultMessage insert(GoodsPO po) throws RemoteException;

    public ResultMessage delete(GoodsPO po) throws RemoteException;

    public ResultMessage update(GoodsPO po) throws RemoteException;

    public GoodsPO detail(GoodsPO po) throws RemoteException;

}
