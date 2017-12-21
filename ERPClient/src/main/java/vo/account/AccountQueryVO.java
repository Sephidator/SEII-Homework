package main.java.vo.account;

import main.java.po.account.AccountQueryPO;

public class AccountQueryVO{

    public String bankAccount;//账户银行账号

    public String name;//账户名字

    public AccountQueryVO(String bankAccount,String name){
        this.bankAccount=bankAccount;
        this.name=name;
    }

    /*生成一个AccountQueryPO*/
    public AccountQueryPO getAccountQueryPO(){
        AccountQueryPO accountQueryPO = new AccountQueryPO(this.bankAccount,this.name);
        return accountQueryPO;
    }
}
