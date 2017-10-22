package server_dataservicestub.initialdataservicestub;


import data.datautility.ResultMessage;
import dataservice.initialdataservice.InitialDataService;
import po.InitialPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by wangn on 2017.10.21.
 */
public class InitialDataServiceStub implements InitialDataService{

    @Override
    public InitialPO find(int year) throws RemoteException {
        ArrayList<String> goodsPOArrayList = new ArrayList<String>();
        ArrayList<String> clientPOArrayList = new ArrayList<String>();
        ArrayList<String> accountPOArrayList = new ArrayList<String>();
        return new InitialPO(2017,goodsPOArrayList,clientPOArrayList,accountPOArrayList);
    }

    @Override
    public ResultMessage insert(InitialPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(InitialPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(InitialPO po) throws RemoteException {
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
