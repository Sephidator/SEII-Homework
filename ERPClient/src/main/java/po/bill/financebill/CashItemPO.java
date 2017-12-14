package main.java.po.bill.financebill;

import java.io.Serializable;

public class CashItemPO implements Serializable {
    public String itemName;// 条目名
    public double amount;//金额
    public String comment;//备注

    public CashItemPO(String itemName, double amount, String comment) {
        this.itemName = itemName;
        this.amount = amount;
        this.comment = comment;
    }
}
