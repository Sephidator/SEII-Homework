package server_dataservicestub.userdataservicestub;

import data.datautility.ResultMessage;
import dataservice.userdataservice.UserDataService;
import po.user.UserPO;
import po.user.UserQueryPO;

import java.util.ArrayList;

public class UserDataServiceStub implements UserDataService {
    @Override
    public ArrayList<UserPO> find(UserQueryPO query) {
        ArrayList<UserPO> list = new ArrayList<>();
        list.add(new UserPO());
        list.add(new UserPO());
        list.add(new UserPO());
        list.add(new UserPO());
        return list;
    }

    @Override
    public ResultMessage insert(UserPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(UserPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(UserPO po) {
        return ResultMessage.success;
    }

    @Override
    public String getUserID() {
        return "678910";
    }
}
