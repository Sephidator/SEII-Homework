package po;

import java.util.Date;
import java.util.HashMap;

/**
 * 销售单PO类
 * */
public class SaleTradeBillPO extends SaleBillPO {
    private double totalBeforeDiscount; // 折让前总额
    private double discount; // 折让金额
    private double amountOfVoucher; // 代金卷金额
    private double totalAfterDiscount; // 折让后总额

    public SaleTradeBillPO(String ID, String clientID, String repositoryID, String salesmanID,
                           String operatorID, HashMap<String, Integer> goodsList,
                           double totalBeforeDiscount, double discount, double amountOfVoucher,
                           double totalAfterDiscount, String comment, int state, Date time) {
        this.ID = ID;
        this.clientID = clientID;
        this.repositoryID = repositoryID;
        this.salesmanID = salesmanID;
        this.operatorID = operatorID;
        this.goodsList = goodsList;
        this.totalBeforeDiscount = totalBeforeDiscount;
        this.discount = discount;
        this.amountOfVoucher = amountOfVoucher;
        this.totalAfterDiscount = totalAfterDiscount;
        this.comment = comment;
        this.state = state;
        this.time=time;
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
}
