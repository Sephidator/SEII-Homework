package main.java.data_stub.clientdataservicestub;

import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.po.client.ClientPO;
import main.java.po.client.ClientQueryPO;

import java.util.ArrayList;

public class ClientDataServiceStub implements ClientDataService {
    @Override
    public ClientPO find(String clientID) {
        return new ClientPO();
    }

    @Override
    public ArrayList<ClientPO> finds(ClientQueryPO query) {
        ArrayList<ClientPO> list = new ArrayList<>();
        list.add(new ClientPO());
        return list;
    }

    @Override
    public String insert(ClientPO po) {
        return "00000001";
    }

    @Override
    public void delete(String clientID) {

    }

    @Override
    public void update(ClientPO po) {

    }


}
