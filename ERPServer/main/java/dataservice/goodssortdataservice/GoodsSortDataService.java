package dataservice.goodssortdataservice;

import po.GoodsSortPO;
import data.datautility.ResultMessage;
import java.rmi.RemoteException;

public interface GoodsSortDataService {

    public GoodsSortPO findGoodsSort(String ID) throws RemoteException;

    public ResultMessage insert(GoodsSortPO po) throws RemoteException;

    public ResultMessage delete(GoodsSortPO po) throws RemoteException;

    public ResultMessage update(GoodsSortPO po) throws RemoteException;

    public GoodsSortPO detail(GoodsSortPO po) throws RemoteException;

}
