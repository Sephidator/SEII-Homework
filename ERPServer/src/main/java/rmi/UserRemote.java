package main.java.rmi;

import main.java.data.userdata.UserData;
import main.java.dataservice.userdataservice.UserDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserRemote extends UnicastRemoteObject {
    private UserDataService userDataService;

    public UserRemote() throws RemoteException {
        try {
            userDataService = new UserData();
            Naming.rebind("rmi://127.0.0.1:7200/UserDataService", userDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
