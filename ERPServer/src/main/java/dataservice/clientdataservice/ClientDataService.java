package main.java.dataservice.clientdataservice;

import main.java.po.client.ClientPO;
import main.java.po.client.ClientQueryPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientDataService extends Remote {
    ArrayList<ClientPO> find(ClientQueryPO query) throws RemoteException;

    String insert(ClientPO po) throws RemoteException;

    void delete(String clientID) throws RemoteException;

    void update(ClientPO po) throws RemoteException;
}
