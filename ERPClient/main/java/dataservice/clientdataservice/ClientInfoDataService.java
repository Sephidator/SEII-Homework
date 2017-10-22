package dataservice.clientdataservice;

import po.ClientPO;

import java.rmi.RemoteException;

public interface ClientInfoDataService {
    public ClientPO getClient(String ID) throws RemoteException;
}
