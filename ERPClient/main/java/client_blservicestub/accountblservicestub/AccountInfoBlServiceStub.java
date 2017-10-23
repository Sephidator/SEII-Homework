package client_blservicestub.accountblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.accountblservice.AccountInfoBlService;

/**
 * Created by wangn on 2017.10.21.
 */
public class AccountInfoBlServiceStub implements AccountInfoBlService {

    @Override
    public ResultMessage mockAccountName(String accountID, String newName) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delAccount(String accountID) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage updateAccount() {
        return ResultMessage.success;
    }
}
