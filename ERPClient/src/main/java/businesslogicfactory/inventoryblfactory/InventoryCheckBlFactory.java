package main.java.businesslogicfactory.inventoryblfactory;

import main.java.businesslogic.inventorybl.InventoryCheckBl;
import main.java.businesslogicservice.inventoryblservice.InventoryCheckBlService;

public class InventoryCheckBlFactory {
    public InventoryCheckBlService getService(){
        InventoryCheckBlService inventoryCheckBlService=new InventoryCheckBl();
        return inventoryCheckBlService;
    }
}
