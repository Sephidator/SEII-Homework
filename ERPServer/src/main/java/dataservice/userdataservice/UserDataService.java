package main.java.dataservice.userdataservice;

import main.java.data.datautility.ResultMessage;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;

import java.util.ArrayList;

public interface UserDataService {
    public ArrayList<UserPO> find(UserQueryPO query);

    public ResultMessage insert(UserPO po);

    public ResultMessage delete(UserPO po);

    public ResultMessage update(UserPO po);

    public String getUserID();
}
