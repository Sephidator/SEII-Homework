package main.java.dataservice.messagedataservice;

import main.java.po.message.MessagePO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MessageDataService extends Remote {
    ArrayList<MessagePO> finds(String receiverID) throws RemoteException;

    void insert(MessagePO message) throws RemoteException;
}
