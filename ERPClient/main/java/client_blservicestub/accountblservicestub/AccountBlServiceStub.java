package stub_driver.Client.main.java;
import businesslogicservice.accountblservice.AccountBlService;
import po.UserPO;
import vo.AccountVO;

import java.util.ArrayList;

/**
 * Created by wangn on 2017.10.21.
 */
public class AccountBlServiceStub implements AccountBlService{

    @Override
    public ResultMessage isPrimeRight(UserPO po) {
        if(po.isTop())
            return ResultMessage.Success;
        else
            return ResultMessage.Success;
    }

    @Override
    public ResultMessage addAccount(String accountID, String name) {
        return ResultMessage.Success;
    }

    @Override
    public AccountVO showAccount(String accountID) {
        return new AccountVO("6212262402017123456","wang",0);
    }

    @Override
    public String getAccountID(AccountVO vo) {
        return "6212262402017123456";
    }

    @Override
    public String getAccountName(String accountID) {
        return "wang";
    }

    @Override
    public double getAccountRem(String accountID) {
        return 0;
    }

    @Override
    public ArrayList<AccountVO> searchAccount(String keyWords) {
        ArrayList<AccountVO> accountVOArrayList = new ArrayList<AccountVO>();
        accountVOArrayList.add(new AccountVO("6212262402017123456","wang",0));
        return accountVOArrayList;
    }
}
