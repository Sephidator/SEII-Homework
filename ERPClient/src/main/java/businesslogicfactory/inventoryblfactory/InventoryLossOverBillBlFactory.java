package main.java.businesslogicfactory.inventoryblfactory;


import main.java.businesslogic.inventorybl.InventoryLossOverBillBl;
import main.java.businesslogicservice.inventoryblservice.InventoryLossOverBillBlService;

public class InventoryLossOverBillBlFactory {
    public InventoryLossOverBillBlService getService(){
        InventoryLossOverBillBlService inventoryLossOverBillBlService=new InventoryLossOverBillBl();
        return inventoryLossOverBillBlService;
    }
}
