package main.java.datafactory.initialdatafactory;

import main.java.dataservice.initialdataservice.InitialDataService;

import java.rmi.Naming;

public class InitialDataFactory {
    public InitialDataService getService() throws Exception{
        InitialDataService service = (InitialDataService) Naming.lookup("rmi://127.0.0.1:7200/InitialDataService");
        return service;
    }
}
