package main.java.datafactory.accountdatafactory;

import main.java.dataservice.accountdataservice.AccountDataService;

import java.rmi.Naming;

public class AccountDataFactory {
    public static AccountDataService getService() throws Exception{
        AccountDataService service = (AccountDataService) Naming.lookup("rmi://127.0.0.1:7200/AccountDataService");
        return service;
    }
}
