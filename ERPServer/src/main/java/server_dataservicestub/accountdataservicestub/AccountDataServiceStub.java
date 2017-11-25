package main.java.server_dataservicestub.accountdataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.accountdataservice.AccountDataService;
import main.java.po.account.AccountPO;
import main.java.po.account.AccountQueryPO;

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
