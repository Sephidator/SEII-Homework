package main.java.po.user;

import main.java.po.QueryPO;

public class UserQueryPO extends QueryPO {
    public String name;
    public String type;

    public UserQueryPO(String ID, String name, String type) {
        this.ID = ID;
        this.name = name;
        this.type = type;
    }
}
