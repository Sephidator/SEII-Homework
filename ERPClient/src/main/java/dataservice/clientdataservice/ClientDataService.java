package dataservice.clientdataservice;

import businesslogic.blutility.ResultMessage;
import po.client.ClientPO;
import po.client.ClientQueryPO;

import java.util.ArrayList;

public interface ClientDataService {
    public ArrayList<ClientPO> find(ClientQueryPO query);
    public ResultMessage insert(ClientPO po);
    public ResultMessage delete(ClientPO po);
    public ResultMessage update(ClientPO po);
    public String getID();
}
