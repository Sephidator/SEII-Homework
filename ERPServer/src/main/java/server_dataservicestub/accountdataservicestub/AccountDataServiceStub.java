package main.java.server_dataservicestub.accountdataservicestub;


import main.java.data.datautility.ResultMessage;
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
    public ResultMessage insert(AccountPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(AccountPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(AccountPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getAccountID() {
        return "Account00000004";
    }
}
