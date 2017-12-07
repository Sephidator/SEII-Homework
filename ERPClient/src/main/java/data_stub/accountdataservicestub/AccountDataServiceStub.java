package main.java.data_stub.accountdataservicestub;

import main.java.dataservice.accountdataservice.AccountDataService;
import main.java.po.account.AccountPO;
import main.java.po.account.AccountQueryPO;

import java.util.ArrayList;


public class AccountDataServiceStub implements AccountDataService {

    @Override
    public AccountPO find(String accountID) {
        return new AccountPO(accountID+".bankAccount",accountID+".name",123.321);
    }

    @Override
    public ArrayList<AccountPO> finds(AccountQueryPO query) {
        ArrayList<AccountPO> list = new ArrayList<>();
        list.add(new AccountPO(query.bankAccount,query.name,123.321));
        return list;
    }

    @Override
    public String insert(AccountPO po) {
        return po.getID();
    }

    @Override
    public void delete(String accountID) {

    }

    @Override
    public void update(AccountPO po) {

    }
}
