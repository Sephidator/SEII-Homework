package main.java.po.bill.purchasebill;

import main.java.po.promotion.GoodsItemPO;

import java.util.ArrayList;
import java.util.Date;

/**
 * 进货退货单VO类
 * */
public class PurchaseTradeBillPO extends PurchaseBillPO {

    public PurchaseTradeBillPO(String ID, String state, Date time, String type, String operatorID, String comment, String clientID, ArrayList<GoodsItemPO> purchaseList, double total) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operatorID = operatorID;
        this.comment = comment;
        this.clientID=clientID;
        this.purchaseList=purchaseList;
        this.total=total;
    }

    public PurchaseTradeBillPO(){

    }

}
