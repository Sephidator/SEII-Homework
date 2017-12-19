package main.java.datafactory.purchasedatafactory;

import main.java.dataservice.purchasedataservice.PurchaseTradeBillDataService;

import java.rmi.Naming;

public class PurchaseTradeBillDataFactory {
    public static PurchaseTradeBillDataService getService() throws Exception{
        PurchaseTradeBillDataService purchaseTradeBillDataService=(PurchaseTradeBillDataService) Naming.lookup("rmi://127.0.0.1:7200/PurchaseTradeBillDataService");
        return purchaseTradeBillDataService;
    }
}
