package stub_driver.Client.main.java;

import businesslogicservice.accountblservice.AccountInfoBlService;

/**
 * Created by wangn on 2017.10.21.
 */
public class AccountInfoBlServiceStub implements AccountInfoBlService{

    @Override
    public ResultMessage mockAccountName(String accountID, String newName) {
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage delAccount(String accountID) {
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage updateAccount() {
        return ResultMessage.Success;
    }
}
