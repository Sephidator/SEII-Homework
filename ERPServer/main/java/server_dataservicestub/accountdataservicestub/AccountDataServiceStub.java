package server_dataservicestub.accountdataservicestub;

import data.datautility.ResultMessage;
import dataservice.accountdataservice.AccountDataService;
import po.account.AccountPO;
import po.account.AccountQueryPO;

import java.util.ArrayList;

public class AccountDataServiceStub implements AccountDataService{
    @Override
    public AccountPO find(AccountQueryPO query) {
        AccountPO accountPO = new AccountPO("Account-20171106-00001","6212262402011111111","公司甲帐",10000);
        return accountPO;
    }

    @Override
    public String getID() {
        return "Account-20171106-00001";
    }

    @Override
    public ResultMessage insert(AccountPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(AccountPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(AccountPO po) {
        return ResultMessage.success;
    }
}
