package main.java.dataservice.inventorydataservice;

import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InventoryGiftBillDataService extends Remote {

    ArrayList<InventoryGiftBillPO> find(BillQueryPO query) throws RemoteException;

    String insert(InventoryGiftBillPO bill) throws RemoteException;

    void update(InventoryGiftBillPO bill) throws RemoteException;
}
