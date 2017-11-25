package main.java.businesslogic_mock.accountblmock;

import main.java.businesslogic.accountbl.AccountTool;
import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;

import java.util.ArrayList;

public class AccountToolMock implements AccountTool{
    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
        ArrayList<AccountVO> accountVOArrayList = new ArrayList<AccountVO>();
        AccountVO accountVO = new AccountVO();
        accountVOArrayList.add(accountVO);
        return accountVOArrayList;
    }

    @Override
    public ResultMessage editAccount(AccountVO account) {
        return ResultMessage.success;
    }
}
