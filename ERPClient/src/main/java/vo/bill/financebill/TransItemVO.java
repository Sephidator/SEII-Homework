package main.java.vo.bill.financebill;

import main.java.businesslogic.accountbl.AccountBl;
import main.java.businesslogic.accountbl.AccountTool;
import main.java.po.bill.financebill.TransItemPO;
import main.java.vo.account.AccountVO;

public class TransItemVO {
    public AccountVO account;//银行账户
    public double transAmount;//转账金额
    public String comment;//备注

    public TransItemVO(){
        this.account = new AccountVO();
        this.transAmount = 0;
        this.comment = "";
    }
    public TransItemVO(AccountVO account, double transAmount, String comment) {
        this.account = account;
        this.transAmount = transAmount;
        this.comment = comment;
    }

    /*得到TransItemPO*/
    public TransItemPO getTransItemPO(){
        TransItemPO transItemPO = new TransItemPO(this.account.getID(),this.transAmount,this.comment);
        return transItemPO;
    }

    /*将PO转成VO*/
    public TransItemVO(TransItemPO transItemPO) throws Exception{
        AccountTool accountTool = new AccountBl();
        this.account = accountTool.find(transItemPO.accountID);
        this.transAmount = transItemPO.transAmount;
        this.comment = transItemPO.comment;
    }
}
