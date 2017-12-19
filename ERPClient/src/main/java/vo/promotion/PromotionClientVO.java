package main.java.vo.promotion;

import main.java.vo.client.ClientVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsItemVO;

import java.util.ArrayList;
import java.util.Date;

public class PromotionClientVO extends PromotionVO {
    private int clientLevel; // 客户等级

    private double discount; // 折让金额

    private double voucher;//代金券

    private ArrayList<GiftItemVO> giftList; // 赠品列表

    public PromotionClientVO(){
        this.name = "";
        this.type="客户促销策略";
        this.start=new Date();
        this.end=new Date();
        this.clientLevel = 0;
        this.discount = 0;
        this.voucher = 0;
        this.giftList = new ArrayList<>();
    }

    public PromotionClientVO(String name ,Date start, Date end, int clientLevel, double discount,int voucher, ArrayList<GiftItemVO> giftList) {
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

    /*计算适用的促销策略的赠品列表*/
    public ArrayList<GiftItemVO> countGiftList(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        if(client.getLevel() >= this.getClientLevel())
            return this.getGiftList();
        else
            return new ArrayList<>();
    }

    /*计算适用的促销策略的代金券总额*/
    public double countVoucher(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        if(client.getLevel() >= this.getClientLevel())
            return this.getVoucher();
        else
            return 0;
    }

    /*计算适用的促销策略的折让部分*/
    public double countPromotionDiscount(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        if(client.getLevel() >= this.getClientLevel())
            return this.getDiscount();
        else
            return 0;
    }
}
