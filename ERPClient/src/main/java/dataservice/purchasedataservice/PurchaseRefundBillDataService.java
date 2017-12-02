package main.java.dataservice.purchasedataservice;

import main.java.po.bill.BillQueryPO;
import main.java.po.bill.purchasebill.PurchaseRefundBillPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PurchaseRefundBillDataService extends Remote {
    ArrayList<PurchaseRefundBillPO> find(BillQueryPO query) throws RemoteException;

    String insert(PurchaseRefundBillPO po) throws RemoteException;

    void update(PurchaseRefundBillPO po) throws RemoteException;
}
