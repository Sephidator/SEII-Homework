package main.java.businesslogicfactory.inventoryblfactory;

import main.java.businesslogic.inventorybl.InventoryVerificationBl;
import main.java.businesslogicservice.inventoryblservice.InventoryVerificationBlService;

public class InventoryVerificationBlFactory {
    public static InventoryVerificationBlService getService(){
        InventoryVerificationBlService inventoryVerificationBlService=new InventoryVerificationBl();
        return inventoryVerificationBlService;
    }
}
