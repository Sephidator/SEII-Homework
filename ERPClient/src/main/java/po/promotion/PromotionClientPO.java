package main.java.po.promotion;

import main.java.po.goods.GiftItemPO;

import java.util.ArrayList;
import java.util.Date;

public class PromotionClientPO extends PromotionPO {
    private int clientLevel; // 客户等级

    private double voucher; // 代金券

    private ArrayList<GiftItemPO> giftList; // 赠品列表

    public PromotionClientPO() {

    }

    public PromotionClientPO(String ID, String name, Date start, Date end, int clientLevel, double voucher, ArrayList<GiftItemPO> giftList) {
        this.ID = ID;
        this.name = name;
        type = "客户促销策略";
        this.start = start;
        this.end = end;
        this.clientLevel = clientLevel;
        this.voucher = voucher;
        this.giftList = giftList;
    }

    public int getClientLevel() {
        return clientLevel;
    }

    public void setClientLevel(int clientLevel) {
        this.clientLevel = clientLevel;
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
