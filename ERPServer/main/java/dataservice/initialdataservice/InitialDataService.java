package dataservice.initialdataservice;

import java.rmi.RemoteException;
import po.InitialPO;
import businesslogic.blutility.ResultMessage;

/**
 * Created by wangn on 2017.10.21.
 */
public interface InitialDataService {

    public InitialPO find(int year) throws RemoteException;

    public ResultMessage insert(InitialPO po) throws RemoteException;

    public ResultMessage delete(InitialPO po) throws RemoteException;

    public ResultMessage update(InitialPO po) throws RemoteException;

    public ResultMessage init() throws RemoteException;

    public ResultMessage finish() throws RemoteException;
}
