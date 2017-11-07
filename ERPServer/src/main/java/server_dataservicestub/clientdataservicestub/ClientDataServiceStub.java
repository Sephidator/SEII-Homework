package server_dataservicestub.clientdataservicestub;

import data.datautility.ResultMessage;
import dataservice.clientdataservice.ClientDataService;
import po.client.ClientPO;
import po.client.ClientQueryPO;

import java.util.ArrayList;

public class ClientDataServiceStub implements ClientDataService{
    @Override
    public ArrayList<ClientPO> find(ClientQueryPO query) {
        ArrayList<ClientPO> list=new ArrayList<>();
        list.add(new ClientPO());
        return list;
    }

    @Override
    public ResultMessage insert(ClientPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(ClientPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(ClientPO po) {
        return ResultMessage.success;
    }

    @Override
    public String getID() {
        return "123";
    }
}
