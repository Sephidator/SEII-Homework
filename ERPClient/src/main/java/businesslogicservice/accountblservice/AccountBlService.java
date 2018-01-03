package main.java.businesslogicservice.accountblservice;

import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;

import java.util.ArrayList;

public interface AccountBlService{

    ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception;//返回账户列表

    String addAccount(AccountVO account) throws Exception;//添加一个账户，更新账户列表

    void deleteAccount(String accountID) throws Exception;//删除一个账户，更新账户列表

    void editAccount(AccountVO account) throws Exception;//编辑一个账户，更新账户列表
}
