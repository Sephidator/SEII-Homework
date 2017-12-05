package main.java.po.account;

import java.io.Serializable;

public class AccountQueryPO implements Serializable {
    public String bankAccount; //账户银行账号
    public String name; //账户名字

    public AccountQueryPO(String bankAccount, String name) {
        this.bankAccount = bankAccount;
        this.name = name;
    }
}
