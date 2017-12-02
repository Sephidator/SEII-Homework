package main.java.po.client;

import main.java.po.QueryPO;

public class ClientQueryPO extends QueryPO {
    public String name;

    public ClientQueryPO(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
}
