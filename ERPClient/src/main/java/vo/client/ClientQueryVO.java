package main.java.vo.client;

import main.java.po.client.ClientQueryPO;
import main.java.vo.QueryVO;

public class ClientQueryVO extends QueryVO{
    public String name;

    public ClientQueryVO(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public ClientQueryPO getClientQueryPO(){
        ClientQueryPO clientQueryPO=new ClientQueryPO(this.ID,this.name);
        return clientQueryPO;
    }
}
