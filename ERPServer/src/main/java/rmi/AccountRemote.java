package main.java.rmi;

import main.java.data.accountdata.AccountData;
import main.java.dataservice.accountdataservice.AccountDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AccountRemote extends UnicastRemoteObject {
    private AccountDataService accountDataService;

    protected AccountRemote() throws RemoteException {
        try {
            accountDataService = new AccountData();
            Naming.rebind("rmi://127.0.0.1:7200/AccountDataService", accountDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
