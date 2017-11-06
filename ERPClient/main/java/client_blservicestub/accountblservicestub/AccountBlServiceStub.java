package client_blservicestub.accountblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.accountblservice.AccountBlService;
import vo.account.AccountVO;

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
