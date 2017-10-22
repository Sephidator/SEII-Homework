package po;

import java.io.Serializable;

/*
 * 账户数据
 * @author:
 * @version:
 */

public class AccountPO implements Serializable{

    private String bankAccount;//账户银行账号

    private String accountName;//账户名字

    private double remaining;//账户余额

    private boolean viewState = true;//删除账户时，账户不可用
    /*构造函数*/
    public AccountPO(String bankAcc, String name, double rem) {
        newAccount(bankAcc, name, rem);
    }

    /*传入账号和名字以及余额，建立新账户，成功返回true*/
    public boolean newAccount(String bankAcc, String name, double rem) {
        this.bankAccount = bankAcc;
        this.accountName = name;
        this.remaining = rem;//TODO 这里会有账户余额的初始值0.0，所以新建的时候允许输入余额吗？
        return true;
    }

    /*返回账户的账号*/
    //用于模糊查询以及后续功能的调用
    public String getAccount() {
        return this.bankAccount;
    }

    /*返回账户的名字*/
    public String getAccountName() {
        return this.accountName;
    }

    /*修改账户的名字，成功返回true*/
    public boolean setAccountName(String name) {
        this.accountName = name;
        return true;
    }

    /*返回账户的余额*/
    public double getRemaing() {
        return this.remaining;
    }

    /*返回账户的可见性*/
    public boolean getViewState() {
        return this.viewState;
    }

    /*删除对应账户，成功返回true*/
    public boolean delAccount() {
        this.viewState = false;
        return true;
    }

    /*系统不准用户修改账户余额，账户余额要由系统更改*/
    /*系统修改账户余额，成功返回true*/
    public boolean setRemaining(double rem) {
        this.remaining = rem;
        return true;
    }
}
