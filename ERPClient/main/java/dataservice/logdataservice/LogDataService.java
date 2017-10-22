package dataservice.logdataservice;

import po.LogPO;

import java.rmi.RemoteException;
import businesslogic.blutility.ResultMessage;

/**
 * Created by wangn on 2017.10.21.
 */
public interface LogDataService {

    /*根据年份year查找并返回相应的LogPO结果*/
    public LogPO find(int year) throws RemoteException;

    /*在持久化数据中插入一个po记录*/
    public ResultMessage insert(LogPO po) throws RemoteException;

    /*删除一个po*/
    public ResultMessage delete(LogPO po) throws RemoteException;

    /*更新一个po*/
    public ResultMessage update(LogPO po) throws RemoteException;

    /*初始化持久化数据库*/
    public ResultMessage init() throws RemoteException;

    /*结束持久化数据库*/
    public ResultMessage finish() throws RemoteException;
}
