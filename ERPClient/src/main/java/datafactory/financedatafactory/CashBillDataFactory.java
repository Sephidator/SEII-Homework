package main.java.datafactory.financedatafactory;

import main.java.dataservice.financedataservice.CashBillDataService;

import java.rmi.Naming;

public class CashBillDataFactory {
    public CashBillDataService getService() throws Exception{
        CashBillDataService service = (CashBillDataService) Naming.lookup("rmi://127.0.0.1:7200/CashBillDataService");
        return service;
    }
}
