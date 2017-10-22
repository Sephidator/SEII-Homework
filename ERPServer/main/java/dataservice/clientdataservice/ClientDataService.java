package dataservice.clientdataservice;

import data.datautility.ResultMessage;
import po.ClientPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientDataService {
    public ResultMessage add(ClientPO clientpo) throws RemoteException;
    public ArrayList<ClientPO> find(String clientInfo) throws RemoteException;
    public ResultMessage  delete (ClientPO clientpo) throws RemoteException;
    public ResultMessage update (ClientPO clientpo) throws RemoteException;
}
