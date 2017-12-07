package main.java.businesslogic_mock.accountblmock;

import main.java.businesslogic.accountbl.AccountTool;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;

import java.util.ArrayList;

public class AccountToolMock implements AccountTool {
    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception{//查询方式有账号和名称
        ArrayList<AccountVO> accountVOArrayList = new ArrayList<AccountVO>();
        AccountVO accountVO = new AccountVO(query.bankAccount, query.name,123.321);//账号、名字和余额
        accountVOArrayList.add(accountVO);
        return accountVOArrayList;
    }

    @Override
    public void editAccount(AccountVO account) throws Exception{}

    @Override
    public AccountVO find(String accountID) throws Exception {
        AccountVO accountVO = new AccountVO(accountID+".bankAccount", accountID+".query.name",123.321);//账号、名字和余额
        return accountVO;
    }
}
