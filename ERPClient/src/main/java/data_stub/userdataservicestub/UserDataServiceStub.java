package main.java.data_stub.userdataservicestub;

import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class UserDataServiceStub implements UserDataService {

    @Override
    public UserPO find(String userID) {
        return new UserPO();
    }

    @Override
    public ArrayList<UserPO> finds(UserQueryPO query) {
        ArrayList<UserPO> list = new ArrayList<>();
        list.add(new UserPO());
        return list;
    }

    @Override
    public String insert(UserPO po) {
        return "00000001";
    }

    @Override
    public void delete(String userID) throws RemoteException {

    }

    @Override
    public void update(UserPO po) throws RemoteException {

    }
}
