package main.java.datafactory.saledatafactory;

import main.java.dataservice.saledataservice.SaleTradeBillDataService;

import java.rmi.Naming;

public class SaleTradeBillDataFactory {
    public static SaleTradeBillDataService getService() throws Exception{
        SaleTradeBillDataService saleTradeBillDataService=(SaleTradeBillDataService) Naming.lookup("rmi://127.0.0.1:7200/SaleTradeBillDataService");
        return saleTradeBillDataService;
    }
}
