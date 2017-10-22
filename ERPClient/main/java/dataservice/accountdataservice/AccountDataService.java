package dataservice.accountdataservice;

import java.rmi.RemoteException;

import businesslogic.blutility.ResultMessage;
import po.UserPO;
import po.AccountPO;


/**
 * Created by wangn on 2017.10.21.
 */
public interface AccountDataService {


    /*查找用户是否为最高权限*/
    public String rightControl(UserPO po) throws RemoteException;

    /*根据账户ID查找并返回相应的AccountPO*/
    public AccountPO find(String accountID) throws RemoteException;

    /*在持久化数据中插入一个po记录*/
    public ResultMessage insert(AccountPO po) throws RemoteException;

    /*删除一个po*/
    public ResultMessage delete(AccountPO po) throws RemoteException;

    /*更新一个po*/
    public ResultMessage update(AccountPO po) throws RemoteException;

    /*初始化持久化数据库*/
    public ResultMessage init() throws RemoteException;

    /*结束持久化数据库*/
    public ResultMessage finish() throws RemoteException;

}
