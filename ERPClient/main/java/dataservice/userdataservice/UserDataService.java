package dataservice.userdataservice;

import businesslogic.blutility.ResultMessage;
import po.PromotionPO;
import po.UserPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UserDataService {
    ArrayList<UserPO> finds() throws RemoteException;

    UserPO find(String id) throws RemoteException;

    ResultMessage insert(UserPO po) throws RemoteException;

    ResultMessage update(UserPO po) throws RemoteException;

    ResultMessage delete(UserPO po) throws RemoteException;
}
