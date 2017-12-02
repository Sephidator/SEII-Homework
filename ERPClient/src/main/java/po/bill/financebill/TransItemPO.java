package main.java.po.bill.financebill;

import java.io.Serializable;

public class TransItemPO implements Serializable {
    public String bankAccount; //银行账户
    public double transAmount; //转账金额
    public String comment; //备注

    public TransItemPO(String bankAccount, double transAmount, String comment) {
        this.bankAccount = bankAccount;
        this.transAmount = transAmount;
        this.comment = comment;
    }
}
