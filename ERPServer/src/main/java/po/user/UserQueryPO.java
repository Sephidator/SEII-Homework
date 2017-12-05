package main.java.po.user;

import java.io.Serializable;

public class UserQueryPO implements Serializable {
    public String name;
    public String type;

    public UserQueryPO(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
