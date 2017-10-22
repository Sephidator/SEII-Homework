package stub_driver.Server.main.java;

import businesslogic.blutility.ResultMessage;
import dataservice.accountdataservice.AccountDataService;
import po.AccountPO;
import po.UserPO;

import java.rmi.RemoteException;

/**
 * Created by wangn on 2017.10.21.
 */
public class AccountDataServiceStub implements AccountDataService{

    @Override
    public String rightControl(UserPO po) throws RemoteException {
        if(po.isTop())
            return "true";
        else
            return "false";
    }

    @Override
    public AccountPO find(String accountID) throws RemoteException {
        return new AccountPO("6212262402017123456","wang",0);
    }

    @Override
    public ResultMessage insert(AccountPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(AccountPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(AccountPO po) throws RemoteException {
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
