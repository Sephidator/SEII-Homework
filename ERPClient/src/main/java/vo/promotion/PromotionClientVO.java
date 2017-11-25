package main.java.vo.promotion;

import java.util.ArrayList;
import java.util.Date;

public class PromotionClientVO extends PromotionVO {
    private int clientLevel; // 客户等级

    private double voucher;//代金券

    private ArrayList<GiftItemVO> giftList; // 赠品列表

    PromotionClientVO(){

    }

    public PromotionClientVO(String ID, String type, Date start, Date end, int clientLevel, int voucher, ArrayList<GiftItemVO> giftList) {
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

    public ArrayList<GiftItemVO> getGiftList() {
        return giftList;
    }

    public void setClientLevel(int clientLevel) {
        this.clientLevel = clientLevel;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
    }

    public void setGiftList(ArrayList<GiftItemVO> giftList) {
        this.giftList = giftList;
    }
}
