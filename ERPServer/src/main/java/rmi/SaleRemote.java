package main.java.rmi;

import main.java.data.saledata.SaleRefundBillData;
import main.java.data.saledata.SaleTradeBillData;
import main.java.dataservice.saledataservice.SaleRefundBillDataService;
import main.java.dataservice.saledataservice.SaleTradeBillDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SaleRemote extends UnicastRemoteObject {
    private SaleRefundBillDataService saleRefundBillDataService;
    private SaleTradeBillDataService saleTradeBillDataService;

    public SaleRemote() throws RemoteException {
        try {
            saleRefundBillDataService = new SaleRefundBillData();
            Naming.rebind("rmi://127.0.0.1:7200/SaleRefundBillDataService", saleRefundBillDataService);
            saleTradeBillDataService = new SaleTradeBillData();
            Naming.rebind("rmi://127.0.0.1:7200/SaleTradeBillDataService", saleTradeBillDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
