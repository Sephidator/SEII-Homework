package main.java.datafactory.clientdatafactory;

import main.java.dataservice.clientdataservice.ClientDataService;

import java.rmi.Naming;

public class ClientDataFactory {
    public ClientDataService getService() throws Exception{
        ClientDataService service = (ClientDataService) Naming.lookup("rmi://127.0.0.1:7200/ClientDataService");
        return service;
    }
}
