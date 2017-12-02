package main.java.po.bill.financebill;

import java.util.ArrayList;
import java.util.Date;

public class PaymentBillPO extends FinanceBillPO {

    private String clientID; //付款单的客户编号

    private ArrayList<TransItemPO> transList; //付款单转账列表的银行账户和金额

    public PaymentBillPO() {

    }

    public PaymentBillPO(String state, Date time, String operatorID, String comment, double total, String clientID, ArrayList<TransItemPO> transList) {
        this.state = state;
        this.time = time;
        type = "付款单";
        this.operatorID = operatorID;
        this.comment = comment;
        this.total = total;
        this.clientID = clientID;
        this.transList = transList;
    }

    public String getClientID() {
        return clientID;
    }

    public ArrayList<TransItemPO> getTransList() {
        return transList;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setTransList(ArrayList<TransItemPO> transList) {
        this.transList = transList;
    }
}

