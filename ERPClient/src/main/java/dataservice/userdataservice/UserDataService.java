package main.java.dataservice.userdataservice;

import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UserDataService extends Remote {
    UserPO find(String userID) throws RemoteException;

    ArrayList<UserPO> finds(UserQueryPO query) throws RemoteException;

    String insert(UserPO po) throws RemoteException;

    void delete(String userID) throws RemoteException;

    void update(UserPO po) throws RemoteException;

    UserPO login(String jobName, String password) throws RemoteException;

    void logout(String userID) throws RemoteException;
}
