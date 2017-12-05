package main.java.vo.client;

import main.java.po.client.ClientQueryPO;

public class ClientQueryVO{
    public String name;

    public ClientQueryVO(String name) {;
        this.name = name;
    }

    public ClientQueryPO getClientQueryPO(){
        ClientQueryPO clientQueryPO=new ClientQueryPO(this.name);
        return clientQueryPO;
    }
}
