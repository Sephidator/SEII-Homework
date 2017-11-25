package main.java.vo.account;

public class AccountVO{

    private String ID;//账户ID

    private String bankAccount;//账户银行账号

    private String name;//账户名字

    private double remaining;//账户余额

    private boolean visible = true;//账户是否存在

    public AccountVO() {
    }

    public AccountVO(String ID, String bankAccount, String name, double remaining) {
        this.ID = ID;
        this.bankAccount = bankAccount;
        this.name = name;
        this.remaining = remaining;
    }

    public String getID() {
        return ID;
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

    public boolean isVisible() {
        return visible;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
