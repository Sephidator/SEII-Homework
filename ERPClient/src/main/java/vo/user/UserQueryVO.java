package main.java.vo.user;

import main.java.po.user.UserQueryPO;
import main.java.vo.QueryVO;

public class UserQueryVO extends QueryVO{
    public String name;
    public String type;

    public UserQueryVO(String ID, String name, String type) {
        this.ID = ID;
        this.name = name;
        this.type = type;
    }

    public UserQueryPO getUserQueryPO(){
        UserQueryPO userQueryPO=new UserQueryPO(this.ID,this.name,this.type);
        return userQueryPO;
    }
}
