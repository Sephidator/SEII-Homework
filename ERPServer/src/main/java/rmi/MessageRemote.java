package main.java.rmi;

import main.java.data.logdata.LogData;
import main.java.data.messagedata.MessageData;
import main.java.dataservice.messagedataservice.MessageDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MessageRemote extends UnicastRemoteObject {
    private MessageDataService messageDataService;

    protected MessageRemote() throws RemoteException {
        try {
            messageDataService = new MessageData();
            Naming.rebind("rmi://127.0.0.1:7200/MessageDataService", messageDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
