package main.java.rmi;

import main.java.data.logdata.LogData;
import main.java.dataservice.logdataservice.LogDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LogRemote extends UnicastRemoteObject {
    private LogDataService logDataService;

    public LogRemote() throws RemoteException {
        try {
            logDataService = new LogData();
            Naming.rebind("rmi://127.0.0.1:7200/LogDataService", logDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
