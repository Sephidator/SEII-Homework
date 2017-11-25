package main.java.client_blservicestub.accountblservicestub;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.accountblservice.AccountBlService;
import main.java.vo.account.AccountVO;

public class AccountBlServiceStub implements AccountBlService{
    @Override
    public String getID() {
        return "Account-20171106-00001";
    }

    @Override
    public ResultMessage addAccount(AccountVO account) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage deleteAccount(AccountVO account) {
        return ResultMessage.success;
    }
}
