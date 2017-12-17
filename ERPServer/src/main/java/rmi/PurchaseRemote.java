package main.java.rmi;

import main.java.data.purchasedata.PurchaseRefundBillData;
import main.java.data.purchasedata.PurchaseTradeBillData;
import main.java.dataservice.purchasedataservice.PurchaseRefundBillDataService;
import main.java.dataservice.purchasedataservice.PurchaseTradeBillDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PurchaseRemote extends UnicastRemoteObject {
    private PurchaseRefundBillDataService purchaseRefundBillDataService;
    private PurchaseTradeBillDataService purchaseTradeBillDataService;

    public PurchaseRemote() throws RemoteException {
        try {
            purchaseRefundBillDataService = new PurchaseRefundBillData();
            Naming.rebind("rmi://127.0.0.1:7200/PurchaseRefundBillDataService", purchaseRefundBillDataService);
            purchaseTradeBillDataService = new PurchaseTradeBillData();
            Naming.rebind("rmi://127.0.0.1:7200/PurchaseTradeBillDataService", purchaseTradeBillDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
