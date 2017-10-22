package vo;

import java.io.Serializable;

/*
 * 账户数据
 * @author:
 * @version:
 */

public class AccountVO implements Serializable{

    private String bankAccount;//账户银行账号

    private String accountName;//账户名字

    private double remaining;//账户余额

    private boolean viewStatus = true;//删除账户时，账户不可用
    /*构造函数*/
    public AccountVO(String bankAcc, String name, double rem) {
        newAccount(bankAcc, name, rem);
    }

    /*传入账号和名字以及余额，建立新账户，成功返回true*/
    public boolean newAccount(String bankAcc, String name, double rem) {
        this.bankAccount = bankAcc;
        this.accountName = name;
        this.remaining = rem;
        return true;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getRemaining() {
        return remaining;
    }

    public boolean isViewStatus() {
        return viewStatus;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "[账号=" + this.bankAccount + ", " + "账号名称=" + this.accountName + ", 账户余额=" + this.remaining
                + ", 可见状态=" + this.viewStatus + "]";
    }
}
