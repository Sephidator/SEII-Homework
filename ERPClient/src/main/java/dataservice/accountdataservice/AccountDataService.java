package main.java.dataservice.accountdataservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.po.account.AccountPO;
import main.java.po.account.AccountQueryPO;

public interface AccountDataService {

    /*根据筛选条件进行查找账户*/
    public AccountPO find(AccountQueryPO query);

    /*取得账户ID*/
    public String getID();

    /*添加账户*/
    public ResultMessage insert(AccountPO po);

    /*删除账户*/
    public ResultMessage delete(AccountPO po);

    /*更改账户信息*/
    public ResultMessage update(AccountPO po);
}
