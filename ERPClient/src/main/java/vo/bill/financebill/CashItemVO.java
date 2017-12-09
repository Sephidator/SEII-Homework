package main.java.vo.bill.financebill;

import main.java.po.bill.financebill.CashItemPO;

public class CashItemVO {

    public String ItemName;// 条目名
    public double amount;//金额
    public String comment;//备注

    public CashItemVO(){
        ItemName = "";
        this.amount = 0;
        this.comment = "";
    }
    public CashItemVO(String itemName, double amount, String comment) {
        ItemName = itemName;
        this.amount = amount;
        this.comment = comment;
    }


    /*得到CashItemPO*/
    public CashItemPO getCashItemPO(){
        CashItemPO cashItemPO = new CashItemPO(this.ItemName,this.amount,this.comment);
        return cashItemPO;
    }

    /*得到CashItemVO*/
    public CashItemVO(CashItemPO cashItemPO){
        this.ItemName = cashItemPO.ItemName;
        this.amount = cashItemPO.amount;
        this.comment = cashItemPO.comment;
    }
}
