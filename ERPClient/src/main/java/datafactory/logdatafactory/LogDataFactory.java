package main.java.datafactory.logdatafactory;

import main.java.dataservice.logdataservice.LogDataService;

import java.rmi.Naming;

public class LogDataFactory {
    public LogDataService getService() throws Exception{
        LogDataService service = (LogDataService) Naming.lookup("rmi://127.0.0.1:7200/LogDataService");
        return service;
    }
}
