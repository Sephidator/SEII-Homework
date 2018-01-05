package main.java.businesslogic.accountbl;

import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;

import java.util.ArrayList;

public interface AccountTool {

    ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception;//返回账户列表

    void editAccount(AccountVO account) throws Exception;//编辑一个账户，更新账户列表

    AccountVO find(String accountID) throws Exception;//通过账户ID查找相应账户
}
