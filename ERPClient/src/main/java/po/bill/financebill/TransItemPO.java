package main.java.po.bill.financebill;

import java.io.Serializable;

public class TransItemPO implements Serializable {
    public String accountID; //银行账户的ID
    public double transAmount; //转账金额
    public String comment; //备注

    public TransItemPO(String accountID, double transAmount, String comment) {
        this.accountID = accountID;
        this.transAmount = transAmount;
        this.comment = comment;
    }
}
