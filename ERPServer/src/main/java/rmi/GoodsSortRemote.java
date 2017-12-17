package main.java.rmi;

import main.java.data.goodssortdata.GoodsSortData;
import main.java.dataservice.goodssortdataservice.GoodsSortDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GoodsSortRemote extends UnicastRemoteObject {
    private GoodsSortDataService goodsSortDataService;

    protected GoodsSortRemote() throws RemoteException {
        try {
            goodsSortDataService = new GoodsSortData();
            Naming.rebind("rmi://127.0.0.1:7200/GoodsSortDataService", goodsSortDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
