package main.java.businesslogicfactory.purchaseblfactory;

import main.java.businesslogic.purchasebl.PurchaseTradeBillBl;
import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;

public class PurchaseTradeBillBlFactory {
    public PurchaseTradeBillBlService getService(){
        PurchaseTradeBillBlService purchaseTradeBillBlService=new PurchaseTradeBillBl();
        return purchaseTradeBillBlService;
    }
}
