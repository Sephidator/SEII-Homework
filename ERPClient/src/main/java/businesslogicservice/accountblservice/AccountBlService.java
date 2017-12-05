package main.java.businesslogicservice.accountblservice;

import main.java.vo.account.AccountVO;

public interface AccountBlService{

    public String addAccount(AccountVO account) throws Exception;//添加一个账户，更新账户列表

    public void deleteAccount(String accountID) throws Exception;//删除一个账户，更新账户列表

}
