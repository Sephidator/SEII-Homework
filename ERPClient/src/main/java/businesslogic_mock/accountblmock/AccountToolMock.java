package main.java.businesslogic_mock.accountblmock;

import main.java.businesslogic.accountbl.AccountTool;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;

import java.util.ArrayList;

public class AccountToolMock implements AccountTool {
    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public void editAccount(AccountVO account) throws Exception {

    }

    @Override
    public AccountVO find(String accountID) throws Exception {
        return new AccountVO();
    }
}
