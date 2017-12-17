package main.java.datafactory.financedatafactory;

import main.java.dataservice.financedataservice.ReceiptBillDataService;

import java.rmi.Naming;

public class ReceiptBillDataFactory {
    public ReceiptBillDataService getService() throws Exception{
        ReceiptBillDataService service = (ReceiptBillDataService) Naming.lookup("rmi://127.0.0.1:7200/ReceiptBillDataService");
        return service;
    }
}
