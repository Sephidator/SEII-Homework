package main.java.po.bill.purchasebill;

import main.java.po.goods.GoodsItemPO;

import java.util.ArrayList;
import java.util.Date;

public class PurchaseRefundBillPO extends PurchaseBillPO {

    public PurchaseRefundBillPO(String state, Date time, String operatorID, String comment, String clientID, ArrayList<GoodsItemPO> purchaseList, double total) {
        this.state = state;
        this.time = time;
        type = "进货退货单";
        this.operatorID = operatorID;
        this.comment = comment;
        this.clientID = clientID;
        this.purchaseList = purchaseList;
        this.total = total;
    }

    public PurchaseRefundBillPO() {

    }

}