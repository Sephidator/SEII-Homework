package main.java.datafactory.inventorydatafactory;

import main.java.dataservice.inventorydataservice.InventoryLossOverBillDataService;

import java.rmi.Naming;

public class InventoryLossOverBillDataFactory {
    public InventoryLossOverBillDataService getService() throws Exception{
        InventoryLossOverBillDataService inventoryLossOverBillDataService=(InventoryLossOverBillDataService) Naming.lookup("rmi://127.0.0.1:7200/InventoryLossOverBillDataService");
        return inventoryLossOverBillDataService;
    }
}
