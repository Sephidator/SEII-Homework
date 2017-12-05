package main.java.dataservice.purchasedataservice;

import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseTradeBillPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PurchaseTradeBillDataService extends Remote {
    ArrayList<PurchaseTradeBillPO> finds(BillQueryPO query) throws RemoteException;

    String insert(PurchaseTradeBillPO po) throws RemoteException;

    void update(PurchaseTradeBillPO po) throws RemoteException;
}
