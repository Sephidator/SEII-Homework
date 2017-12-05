package main.java.dataservice.accountdataservice;

import main.java.po.account.AccountPO;
import main.java.po.account.AccountQueryPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AccountDataService extends Remote {
    /*根据ID查找唯一账户*/
    AccountPO find(String accountID) throws RemoteException;

    /*根据筛选条件进行查找账户*/
    ArrayList<AccountPO> finds(AccountQueryPO query) throws RemoteException;

    /*添加账户*/
    String insert(AccountPO po) throws RemoteException;

    /*删除账户*/
    void delete(String accountID) throws RemoteException;

    /*更改账户信息*/
    void update(AccountPO po) throws RemoteException;
}
