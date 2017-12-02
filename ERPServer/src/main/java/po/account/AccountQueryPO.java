package main.java.po.account;

import main.java.po.QueryPO;

public class AccountQueryPO extends QueryPO {
    public String bankAccount; //账户银行账号
    public String name; //账户名字

    public AccountQueryPO(String ID, String bankAccount, String name) {
        this.ID = ID;
        this.bankAccount = bankAccount;
        this.name = name;
    }
}
