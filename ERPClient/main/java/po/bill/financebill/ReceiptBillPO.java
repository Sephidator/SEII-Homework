package po.bill.financebill;

import java.util.*;

public class ReceiptBillPO extends FinanceBillPO {

    private String clientID;//付款单的客户，包括供应商和销售商组成的字符

    private ArrayList<TransItemPO> transList;//付款单转账列表的银行账户和金额

    public ReceiptBillPO(){

    }

    public ReceiptBillPO(String ID, String state, Date time, String type, String operatorID, String comment, double total, String clientID, ArrayList<TransItemPO> transList) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operatorID = operatorID;
        this.comment = comment;
        this.total=total;
        this.clientID=clientID;
        this.transList=transList;
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

