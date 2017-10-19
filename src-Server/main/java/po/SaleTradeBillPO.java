package po;

import java.util.Date;
import java.util.HashMap;

/**
 * 销售单PO类
 * */
public class SaleTradeBillPO extends SaleBillPO {
    private double beforeDiscount; // 折让前总额
    private double discount; // 折让金额
    private double amountOfVoucher; // 代金卷金额
    private double afterDiscount; // 折让后总额

    public SaleTradeBillPO(String ID, String clientID, String repositoryID, String salesmanID,
                           String operatorID, HashMap<String, Integer> goodsList,
                           double beforeDiscount, double discount, double amountOfVoucher,
                           double afterDiscount, String comment, int state, Date time) {
        this.ID = ID;
        this.clientID = clientID;
        this.repositoryID = repositoryID;
        this.salesmanID = salesmanID;
        this.operatorID = operatorID;
        this.goodsList = goodsList;
        this.beforeDiscount = beforeDiscount;
        this.discount = discount;
        this.amountOfVoucher = amountOfVoucher;
        this.afterDiscount = afterDiscount;
        this.comment = comment;
        this.state = state;
        this.time=time;
    }

    public double getBeforeDiscount() {
        return beforeDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getAmountOfCoupon() {
        return amountOfVoucher;
    }

    public double getAfterDiscount() {
        return afterDiscount;
    }

}
