package dataservice.userdataservice;

import businesslogic.blutility.ResultMessage;
import po.user.UserPO;
import po.user.UserQueryPO;

import java.util.ArrayList;

public interface UserDataService {
    public ArrayList<UserPO> find(UserQueryPO query);

    public ResultMessage insert(UserPO po);

    public ResultMessage delete(UserPO po);

    public ResultMessage update(UserPO po);

    public String getUserID();
}
