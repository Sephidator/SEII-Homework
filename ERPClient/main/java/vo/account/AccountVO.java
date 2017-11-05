package vo.account;

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

    private boolean viewState = true;//删除账户时，账户不可用

    public AccountVO(String bankAccount, String accountName, double remaining, boolean viewState) {
        this.bankAccount = bankAccount;
        this.accountName = accountName;
        this.remaining = remaining;
        this.viewState = viewState;
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
        return viewState;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "[账号=" + this.bankAccount + ", " + "账号名称=" + this.accountName + ", 账户余额=" + this.remaining
                + ", 可见状态=" + this.viewState + "]";
    }
}
