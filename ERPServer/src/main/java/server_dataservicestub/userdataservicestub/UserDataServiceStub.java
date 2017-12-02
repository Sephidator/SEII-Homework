package main.java.server_dataservicestub.userdataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class UserDataServiceStub implements UserDataService {

    @Override
    public ArrayList<UserPO> find(UserQueryPO query) throws RemoteException {
        ArrayList<UserPO> list = new ArrayList<>();
        list.add(new UserPO());
        return list;
    }

    @Override
    public ResultMessage insert(UserPO po) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(UserPO po) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(UserPO po) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getUserID() throws RemoteException {
        return "User00000004";
    }

    @Override
    public UserPO login(String ID, String password) throws RemoteException {
        return new UserPO("User00000004", "赵四", "总经理", "abc", "123", 33, true);
    }

    @Override
    public ResultMessage logout(UserPO po) throws RemoteException {
        return ResultMessage.SUCCESS;
    }
}
