package main.java.po.promotion;

import main.java.po.goods.GiftItemPO;

import java.util.ArrayList;
import java.util.Date;

public class PromotionTotalPO extends PromotionPO {
    private double total;//总价

    private double voucher;//代金券

    private ArrayList<GiftItemPO> giftList; // 赠品列表

    public PromotionTotalPO() {

    }

    public PromotionTotalPO(String name, Date start, Date end, double total, double voucher, ArrayList<GiftItemPO> giftList) {
        this.name = name;
        type = "总价促销策略";
        this.start = start;
        this.end = end;
        this.total = total;
        this.voucher = voucher;
        this.giftList = giftList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getVoucher() {
        return voucher;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
    }

    public ArrayList<GiftItemPO> getGiftList() {
        return giftList;
    }

    public void setGiftList(ArrayList<GiftItemPO> giftList) {
        this.giftList = giftList;
    }
}
