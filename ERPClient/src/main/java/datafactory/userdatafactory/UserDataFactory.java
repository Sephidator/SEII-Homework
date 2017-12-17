package main.java.datafactory.userdatafactory;

import main.java.dataservice.userdataservice.UserDataService;

import java.rmi.Naming;

public class UserDataFactory {
    public UserDataService getService() throws Exception{
        UserDataService service = (UserDataService) Naming.lookup("rmi://127.0.0.1:7200/UserDataService");
        return service;
    }
}
