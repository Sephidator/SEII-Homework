package main.java.dataservice.logdataservice;

import main.java.po.log.LogPO;
import main.java.po.log.LogQueryPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface LogDataService extends Remote {
    /*根据筛选条件查找符合的日志*/
    ArrayList<LogPO> finds(LogQueryPO query) throws RemoteException;

    /*插入日志*/
    void insert(LogPO po) throws RemoteException;
}
