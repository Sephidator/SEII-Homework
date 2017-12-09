package main.java.vo.promotion;

import main.java.vo.goods.GiftItemVO;

import java.util.ArrayList;
import java.util.Date;

public class PromotionClientVO extends PromotionVO {
    private int clientLevel; // 客户等级

    private double discount; // 折让金额

    private double voucher;//代金券

    private ArrayList<GiftItemVO> giftList; // 赠品列表

    PromotionClientVO(){
        this.ID="";
        this.name = "";
        this.type="客户促销策略";
        this.start=new Date();
        this.end=new Date();
        this.clientLevel = 0;
        this.discount = 0;
        this.voucher = 0;
        this.giftList = new ArrayList<>();
    }

    public PromotionClientVO(String ID,String name ,String type, Date start, Date end, int clientLevel, double discount,int voucher, ArrayList<GiftItemVO> giftList) {
        this.ID=ID;
        this.name = name;
        this.type="客户促销策略";
        this.start=start;
        this.end=end;
        this.clientLevel = clientLevel;
        this.discount = discount;
        this.voucher = voucher;
        this.giftList = giftList;
    }

    public int getClientLevel() {
        return clientLevel;
    }

    public double getVoucher() {
        return voucher;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
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
