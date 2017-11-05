package vo.bill.salebill;

import vo.client.ClientVO;
import vo.promotion.GoodsItemVO;
import vo.promotion.PromotionVO;
import vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * 销售单VO类
 * */
public class SaleTradeBillVO extends SaleBillVO {
    private PromotionVO promotion; // 适用的促销策略
    private double totalBeforeDiscount; // 折让前总额
    private double discount; // 折让金额
    private double amountOfVoucher; // 代金卷金额
    private double totalAfterDiscount; // 折让后总额

    public SaleTradeBillVO(String ID, String state, Date time, String type, UserVO operator, String comment, ClientVO client, UserVO salesman, ArrayList<GoodsItemVO> saleList, PromotionVO promotion, double totalBeforeDiscount, double discount, double amountOfVoucher, double totalAfterDiscount) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operator = operator;
        this.comment = comment;
        this.client=client;
        this.salesman=salesman;
        this.saleList=saleList;
        this.promotion=promotion;
        this.totalBeforeDiscount=totalBeforeDiscount;
        this.discount=discount;
        this.amountOfVoucher=amountOfVoucher;
        this.totalAfterDiscount=totalAfterDiscount;
    }

    public SaleTradeBillVO(){

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

    public PromotionVO getPromotion(){
        return promotion;
    }

    public void setPromotion(PromotionVO promotion) {
        this.promotion = promotion;
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
