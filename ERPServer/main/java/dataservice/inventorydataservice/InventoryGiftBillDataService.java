package dataservice.inventorydataservice;

import data.datautility.ResultMessage;
import po.InventoryGiftBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InventoryGiftBillDataService {
    ArrayList<InventoryGiftBillPO> finds() throws RemoteException;

    InventoryGiftBillPO find(String id) throws RemoteException;

    ResultMessage insert(InventoryGiftBillPO po) throws RemoteException;

    ResultMessage update(InventoryGiftBillPO po) throws RemoteException;

    ResultMessage delete(InventoryGiftBillPO po) throws RemoteException;
}
