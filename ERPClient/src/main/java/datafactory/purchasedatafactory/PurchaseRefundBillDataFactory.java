package main.java.datafactory.purchasedatafactory;

import main.java.dataservice.purchasedataservice.PurchaseRefundBillDataService;

import java.rmi.Naming;

public class PurchaseRefundBillDataFactory {
    public static PurchaseRefundBillDataService getService() throws Exception{
        PurchaseRefundBillDataService purchaseRefundBillDataService=(PurchaseRefundBillDataService) Naming.lookup("rmi://127.0.0.1:7200/PurchaseRefundBillDataService");
        return purchaseRefundBillDataService;
    }
}
