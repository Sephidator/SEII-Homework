package main.java.po.promotion;

import java.util.ArrayList;
import java.util.Date;

public class PromotionClientPO extends PromotionPO {
    private int clientLevel; // 客户等级

    private double voucher;//代金券

    private ArrayList<GiftItemPO> giftList; // 赠品列表

    PromotionClientPO(){

    }

    public PromotionClientPO(String ID, String type, Date start, Date end, int clientLevel, int voucher, ArrayList<GiftItemPO> giftList) {
        this.ID=ID;
        this.type=type;
        this.start=start;
        this.end=end;
        this.clientLevel = clientLevel;
        this.voucher = voucher;
        this.giftList = giftList;
    }

    public int getClientLevel() {
        return clientLevel;
    }

    public double getVoucher() {
        return voucher;
    }

    public ArrayList<GiftItemPO> getGiftList() {
        return giftList;
    }

    public void setClientLevel(int clientLevel) {
        this.clientLevel = clientLevel;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
    }

    public void setGiftList(ArrayList<GiftItemPO> giftList) {
        this.giftList = giftList;
    }
}
