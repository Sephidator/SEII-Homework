package main.java.po.account;

import main.java.po.PO;

public class AccountPO extends PO {
    private String bankAccount;//账户银行账号

    private String name;//账户名字

    private double remaining;//账户余额

    public AccountPO() {
        bankAccount="";
        name="";
        remaining=0;
    }

    public AccountPO(String bankAccount, String name, double remaining) {
        this.bankAccount = bankAccount;
        this.name = name;
        this.remaining = remaining;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRemaining() {
        return remaining;
    }

    public void setRemaining(double remaining) {
        this.remaining = remaining;
    }

}
