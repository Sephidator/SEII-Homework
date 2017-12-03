package main.java.server_dataservicestub.userdataservicestub;

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
    public String insert(UserPO po) throws RemoteException {
        return "00000001";
    }

    @Override
    public void delete(String userID) throws RemoteException {

    }

    @Override
    public void update(UserPO po) throws RemoteException {

    }

    @Override
    public UserPO login(String jobName, String password) throws RemoteException {
        return new UserPO("aa", "总经理", "admin", "admin", 19, false);
    }

    @Override
    public void logout(String userID) throws RemoteException {

    }
}
