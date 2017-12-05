package main.java.dataservice.initialdataservice;

import main.java.po.initial.InitialPO;
import main.java.po.initial.InitialQueryPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InitialDataService extends Remote {
    /*获取持久化对象列表*/
    ArrayList<InitialPO> finds(InitialQueryPO query) throws RemoteException;

    /*添加起初信息*/
    String insert(InitialPO po) throws RemoteException;
}
