package main.java.server_dataservicestub.clientdataservicestub;

import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.po.client.ClientPO;
import main.java.po.client.ClientQueryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientDataServiceStub implements ClientDataService {
    @Override
    public ArrayList<ClientPO> find(ClientQueryPO query) {
        ArrayList<ClientPO> list = new ArrayList<>();
        list.add(new ClientPO());
        return list;
    }

    @Override
    public String insert(ClientPO po) {
        return "00000001";
    }

    @Override
    public void delete(String clientID) throws RemoteException {

    }

    @Override
    public void update(ClientPO po) throws RemoteException {

    }


}
