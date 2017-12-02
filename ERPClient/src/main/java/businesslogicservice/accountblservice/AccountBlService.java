package main.java.businesslogicservice.accountblservice;

import main.java.vo.account.AccountVO;

public interface AccountBlService {

    public void addAccount(AccountVO account);//添加一个账户，更新账户列表

    public void deleteAccount(String accountID);//删除一个账户，更新账户列表

}
