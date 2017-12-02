package main.java.dataservice.userdataservice;

import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UserDataService extends Remote {
    ArrayList<UserPO> find(UserQueryPO query) throws RemoteException;

    String insert(UserPO po) throws RemoteException;

    void delete(String userID) throws RemoteException;

    void update(UserPO po) throws RemoteException;

    UserPO login(String ID, String password) throws RemoteException;

    void logout(UserPO po) throws RemoteException;
}
