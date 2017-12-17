package main.java.rmi;

import main.java.data.clientdata.ClientData;
import main.java.dataservice.clientdataservice.ClientDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientRemote extends UnicastRemoteObject {
    private ClientDataService clientDataService;

    protected ClientRemote() throws RemoteException {
        try {
            clientDataService = new ClientData();
            Naming.rebind("rmi://127.0.0.1:7200/ClientDataService", clientDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
