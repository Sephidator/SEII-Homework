package main.java.po.bill.salebill;

import main.java.po.promotion.GoodsItemPO;

import java.util.ArrayList;
import java.util.Date;

/**
 * 销售单PO类
 * */
public class SaleTradeBillPO extends SaleBillPO {
    private String promotionID; // 适用的促销策略
    private double totalBeforeDiscount; // 折让前总额
    private double discount; // 折让金额
    private double amountOfVoucher; // 代金卷金额
    private double totalAfterDiscount; // 折让后总额

    public SaleTradeBillPO(String ID, String state, Date time, String type, String operatorID, String comment, String clientID, String salesmanID, ArrayList<GoodsItemPO> saleList, String promotionID, double totalBeforeDiscount, double discount, double amountOfVoucher, double totalAfterDiscount) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operatorID = operatorID;
        this.comment = comment;
        this.clientID=clientID;
        this.salesmanID=salesmanID;
        this.saleList=saleList;
        this.promotionID=promotionID;
        this.totalBeforeDiscount=totalBeforeDiscount;
        this.discount=discount;
        this.amountOfVoucher=amountOfVoucher;
        this.totalAfterDiscount=totalAfterDiscount;
    }

    public SaleTradeBillPO(){

    }

    public String getPromotionID() {
        return promotionID;
    }

    public double getTotalBeforeDiscount() {
        return totalBeforeDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getAmountOfVoucher() {
        return amountOfVoucher;
    }

    public double getTotalAfterDiscount() {
        return totalAfterDiscount;
    }

    public void setPromotionID(String promotionID) {
        this.promotionID = promotionID;
    }

    public void setTotalBeforeDiscount(double totalBeforeDiscount) {
        this.totalBeforeDiscount = totalBeforeDiscount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setAmountOfVoucher(double amountOfVoucher) {
        this.amountOfVoucher = amountOfVoucher;
    }

    public void setTotalAfterDiscount(double totalAfterDiscount) {
        this.totalAfterDiscount = totalAfterDiscount;
    }
}
