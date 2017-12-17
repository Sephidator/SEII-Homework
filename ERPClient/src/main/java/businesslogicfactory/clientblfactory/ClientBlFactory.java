package main.java.businesslogicfactory.clientblfactory;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogicservice.clientblservice.ClientBlService;

public class ClientBlFactory {
    public ClientBlService getService(){
        ClientBlService service=new ClientBl();
        return service;
    }
}
