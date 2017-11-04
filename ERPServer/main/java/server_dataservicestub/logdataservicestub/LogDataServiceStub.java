package server_dataservicestub.logdataservicestub;

import data.datautility.ResultMessage;
import dataservice.logdataservice.LogDataService;
import po.LogPO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wangn on 2017.10.21.
 */
public class LogDataServiceStub implements LogDataService{

    @Override
    public LogPO find(int year) throws RemoteException {
        String list="finance";
        return new LogPO(list,null,null);
    }

    @Override
    public ResultMessage insert(LogPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(LogPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(LogPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage init() throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage finish() throws RemoteException {
        return ResultMessage.success;
    }
}
