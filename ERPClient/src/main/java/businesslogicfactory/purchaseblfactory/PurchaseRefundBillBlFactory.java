package main.java.businesslogicfactory.purchaseblfactory;

import main.java.businesslogic.purchasebl.PurchaseRefundBillBl;
import main.java.businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;

public class PurchaseRefundBillBlFactory {
    public static PurchaseRefundBillBlService getService(){
        PurchaseRefundBillBlService purchaseRefundBillBlService=new PurchaseRefundBillBl();
        return purchaseRefundBillBlService;
    }
}
