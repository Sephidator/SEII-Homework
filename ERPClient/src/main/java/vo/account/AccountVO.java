package main.java.vo.account;

import main.java.po.account.AccountPO;
import main.java.vo.VO;

public class AccountVO extends VO {

    private String bankAccount;//账户银行账号

    private String name;//账户名字

    private double remaining;//账户余额

    public AccountVO() {
        this.bankAccount = "";
        this.name = "";
        this.remaining = 0;
    }

    public AccountVO(String bankAccount, String name, double remaining) {
        this.bankAccount = bankAccount;
        this.name = name;
        this.remaining = remaining;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getName() {
        return name;
    }

    public double getRemaining() {
        return remaining;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemaining(double remaining) {
        this.remaining = remaining;
    }

    /*一共五个参数*/
    /*返回AccountPO,不进行处理*/
    public AccountPO getAccountPO(){
        AccountPO accountPO = new AccountPO();
        accountPO.setID(this.ID);
        accountPO.setBankAccount(this.bankAccount);
        accountPO.setName(this.name);
        accountPO.setRemaining(this.remaining);
        accountPO.setVisible(this.visible);

        return accountPO;
    }

    /*AccountVO，不进行处理*/
    public AccountVO(AccountPO accountPO){
        this.ID = accountPO.getID();
        this.bankAccount = accountPO.getBankAccount();
        this.name = accountPO.getName();
        this.remaining = accountPO.getRemaining();
        this.visible = accountPO.isVisible();
    }
}
