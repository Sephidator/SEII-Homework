package main.java.datafactory.inventorydatafactory;

import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;

import java.rmi.Naming;

public class InventoryGiftBillDataFactory {
    public static InventoryGiftBillDataService getService() throws Exception{
        InventoryGiftBillDataService inventoryGiftBillDataService=(InventoryGiftBillDataService) Naming.lookup("rmi://127.0.0.1:7200/InventoryGiftBillDataService");
        return inventoryGiftBillDataService;
    }
}
