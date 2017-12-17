package main.java.rmi;

import main.java.data.initialdata.InitialData;
import main.java.dataservice.initialdataservice.InitialDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InitialRemote extends UnicastRemoteObject {
    private InitialDataService initialDataService = new InitialData();

    protected InitialRemote() throws RemoteException {
        try {
            initialDataService = new InitialData();
            Naming.rebind("rmi://127.0.0.1:7200/InitialDataService", initialDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
