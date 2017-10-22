package dataservice.inventorydataservice;


import data.datautility.ResultMessage;
import po.GoodsPO;
import po.InventoryLossOverBillPO;

import java.rmi.RemoteException;

public interface InventoryLossOverBillDataService {


    public GoodsPO findGoods(String ID, String name) throws RemoteException;

    public ResultMessage insert(InventoryLossOverBillPO po) throws RemoteException;

    public int getState(String ID) throws RemoteException;

    public ResultMessage update(InventoryLossOverBillPO po) throws RemoteException;



}
