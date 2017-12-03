package main.java.server_dataservicestub.accountdataservicestub;

import main.java.dataservice.accountdataservice.AccountDataService;
import main.java.po.account.AccountPO;
import main.java.po.account.AccountQueryPO;

import java.util.ArrayList;


public class AccountDataServiceStub implements AccountDataService {

    @Override
    public ArrayList<AccountPO> find(AccountQueryPO query) {
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
