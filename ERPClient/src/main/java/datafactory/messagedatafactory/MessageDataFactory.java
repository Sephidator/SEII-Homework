package main.java.datafactory.messagedatafactory;

import main.java.dataservice.messagedataservice.MessageDataService;

import java.rmi.Naming;

public class MessageDataFactory {
    public static MessageDataService getService() throws Exception {
        MessageDataService service = (MessageDataService) Naming.lookup("rmi://127.0.0.1:7200/MessageDataService");
        return service;
    }
}
