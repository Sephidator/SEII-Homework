package main.java.vo.bill.financebill;

import main.java.po.bill.financebill.TransItemPO;

public class TransItemVO {
    public String bankAccount;//银行账户
    public double transAmount;//转账金额
    public String comment;//备注

    /*得到TransItemPO*/
    public TransItemPO getTransItemPO(){
        TransItemPO transItemPO = new TransItemPO(this.bankAccount,this.transAmount,this.comment);
        return transItemPO;
    }

    /*将PO转成VO*/
    public TransItemVO(TransItemPO transItemPO){
        this.bankAccount = transItemPO.bankAccount;
        this.transAmount = transItemPO.transAmount;
        this.comment = transItemPO.comment;
    }
}
