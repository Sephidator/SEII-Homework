package main.java.businesslogicfactory.inventoryblfactory;

import main.java.businesslogic.inventorybl.InventoryGiftBillBl;
import main.java.businesslogicservice.inventoryblservice.InventoryGiftBillBlService;

public class InventoryGiftBillBlFactory {
    public InventoryGiftBillBlService getService(){
        InventoryGiftBillBlService inventoryGiftBillBlService=new InventoryGiftBillBl();
        return inventoryGiftBillBlService;
    }
}
