package main.java.server_dataservicestub.clientdataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.po.client.ClientPO;
import main.java.po.client.ClientQueryPO;

import java.util.ArrayList;

public class ClientDataServiceStub implements ClientDataService {
    @Override
    public ArrayList<ClientPO> find(ClientQueryPO query) {
        ArrayList<ClientPO> list = new ArrayList<>();
        list.add(new ClientPO());
        return list;
    }

    @Override
    public ResultMessage insert(ClientPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(ClientPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(ClientPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getClientID() {
        return "Client00000004";
    }
}
