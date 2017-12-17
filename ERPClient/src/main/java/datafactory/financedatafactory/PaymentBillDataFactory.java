package main.java.datafactory.financedatafactory;

import main.java.dataservice.financedataservice.PaymentBillDataService;

import java.rmi.Naming;

public class PaymentBillDataFactory {
    public PaymentBillDataService getService() throws Exception{
        PaymentBillDataService service = (PaymentBillDataService) Naming.lookup("rmi://127.0.0.1:7200/PaymentBillDataService");
        return service;
    }
}
