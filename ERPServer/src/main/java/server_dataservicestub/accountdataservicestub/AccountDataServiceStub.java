package main.java.server_dataservicestub.accountdataservicestub;

import main.java.dataservice.accountdataservice.AccountDataService;
import main.java.po.account.AccountPO;
import main.java.po.account.AccountQueryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;


public class AccountDataServiceStub implements AccountDataService {

    @Override
    public AccountPO find(String accountID) {
        return new AccountPO();
    }

    @Override
    public ArrayList<AccountPO> finds(AccountQueryPO query) {
        ArrayList<AccountPO> list = new ArrayList<>();
        list.add(new AccountPO());
        return list;
    }

    @Override
    public String insert(AccountPO po) {
        return "00000001";
    }

    @Override
    public void delete(String accountID) {

    }

    @Override
    public void update(AccountPO po) {

    }
}
