package main.java.dataservice.clientdataservice;

import main.java.data.datautility.ResultMessage;
import main.java.po.client.ClientPO;
import main.java.po.client.ClientQueryPO;

import java.util.ArrayList;

public interface ClientDataService {
    public ArrayList<ClientPO> find(ClientQueryPO query);
    public ResultMessage insert(ClientPO po);
    public ResultMessage delete(ClientPO po);
    public ResultMessage update(ClientPO po);
    public String getID();
}
