package main.java.rmi;

import main.java.data.financedata.CashBillData;
import main.java.data.financedata.PaymentBillData;
import main.java.data.financedata.ReceiptBillData;
import main.java.dataservice.financedataservice.CashBillDataService;
import main.java.dataservice.financedataservice.PaymentBillDataService;
import main.java.dataservice.financedataservice.ReceiptBillDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FinanceRemote extends UnicastRemoteObject {
    private CashBillDataService cashBillDataService;
    private PaymentBillDataService paymentBillDataService;
    private ReceiptBillDataService receiptBillDataService;

    protected FinanceRemote() throws RemoteException {
        try {
            cashBillDataService = new CashBillData();
            Naming.rebind("rmi://127.0.0.1:7200/CashBillDataService", cashBillDataService);
            paymentBillDataService = new PaymentBillData();
            Naming.rebind("rmi://127.0.0.1:7200/PaymentBillDataService", paymentBillDataService);
            receiptBillDataService = new ReceiptBillData();
            Naming.rebind("rmi://127.0.0.1:7200/ReceiptBillDataService", receiptBillDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
