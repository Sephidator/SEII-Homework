package main.java.rmi;

import main.java.data.inventorydata.InventoryGiftBillData;
import main.java.data.inventorydata.InventoryLossOverBillData;
import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;
import main.java.dataservice.inventorydataservice.InventoryLossOverBillDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InventoryRemote extends UnicastRemoteObject {
    private InventoryGiftBillDataService inventoryGiftBillDataService;
    private InventoryLossOverBillDataService inventoryLossOverBillDataService;

    public InventoryRemote() throws RemoteException {
        try {
            inventoryGiftBillDataService = new InventoryGiftBillData();
            Naming.rebind("rmi://127.0.0.1:7200/InventoryGiftBillDataService", inventoryGiftBillDataService);
            inventoryLossOverBillDataService = new InventoryLossOverBillData();
            Naming.rebind("rmi://127.0.0.1:7200/InventoryLossOverBillDataService", inventoryLossOverBillDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
