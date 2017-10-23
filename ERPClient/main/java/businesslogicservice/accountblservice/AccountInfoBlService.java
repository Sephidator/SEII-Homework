package businesslogicservice.accountblservice;

import businesslogic.blutility.ResultMessage;

/**
 * 账户详情显示账户的属性，提供对该账户的修改权限
 * Created by wangn on 2017.10.18.
 */
public interface AccountInfoBlService {

    /*修改账户属性，成功返回true*/
    public ResultMessage mockAccountName(String accountID, String newName);

    /*删除账户，成功返回true*/
    public ResultMessage delAccount(String accountID);

    /*更新账户的名单列表，比如修改账户或者删除账户以后刷新界面*/
    public ResultMessage updateAccount();
}
