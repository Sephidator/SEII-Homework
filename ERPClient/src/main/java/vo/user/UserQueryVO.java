package main.java.vo.user;

import main.java.po.user.UserQueryPO;

public class UserQueryVO{
    public String name;
    public String type;

    public UserQueryVO(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public UserQueryPO getUserQueryPO(){
        UserQueryPO userQueryPO=new UserQueryPO(this.name,this.type);
        return userQueryPO;
    }
}
