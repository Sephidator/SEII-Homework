package main.java.dataservice.inventorydataservice;

import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InventoryGiftBillDataService extends Remote {

    ArrayList<InventoryGiftBillPO> finds(BillQueryPO query) throws RemoteException;

    String insert(InventoryGiftBillPO po) throws RemoteException;

    void update(InventoryGiftBillPO po) throws RemoteException;
}
