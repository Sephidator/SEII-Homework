package main.java.po.client;

import java.io.Serializable;

public class ClientQueryPO implements Serializable {
    public String name;

    public ClientQueryPO(String name) {
        this.name = name;
    }
}
