package main.java.dataservice.inventorydataservice;

import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InventoryLossOverBillDataService extends Remote {

    ArrayList<InventoryLossOverBillPO> find(BillQueryPO query) throws RemoteException;

    String insert(InventoryLossOverBillPO bill) throws RemoteException;

    void update(InventoryLossOverBillPO bill) throws RemoteException;
}
