package dataservice.purchasedataservice;

import businesslogic.blutility.ResultMessage;
import po.PurchaseTradeBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PurchaseTradeBillDataService {
    public ResultMessage insert(PurchaseTradeBillPO po)throws RemoteException;
    public PurchaseTradeBillPO find(String ID) throws RemoteException;
    public ResultMessage delete (PurchaseTradeBillPO po) throws RemoteException;
    public ResultMessage update (PurchaseTradeBillPO po) throws RemoteException;
    public String getID() throws RemoteException;
    public ArrayList<PurchaseTradeBillPO> getList() throws RemoteException;
}
