package main.java.vo.promotion;

import main.java.po.goods.GiftItemPO;
import main.java.po.promotion.PromotionClientPO;
import main.java.po.promotion.PromotionPO;
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

    public PromotionClientVO(PromotionClientPO promotionClientPO) throws Exception{
        this.ID = promotionClientPO.getID();
        this.name = promotionClientPO.getName();
        this.type ="客户促销策略";
        this.start = promotionClientPO.getStart();
        this.end = promotionClientPO.getEnd();
        this.clientLevel = promotionClientPO.getClientLevel();
        this.discount = promotionClientPO.getDiscount();
        this.voucher = promotionClientPO.getVoucher();

        this.giftList=new ArrayList<>();
        for(GiftItemPO giftItemPO:promotionClientPO.getGiftList()){
            giftList.add(new GiftItemVO(giftItemPO));
        }
    }

    @Override
    public PromotionClientPO getPromotionPO(){
        PromotionClientPO promotionClientPO=new PromotionClientPO();

        promotionClientPO.setID(ID);
        promotionClientPO.setType("客户促销策略");
        promotionClientPO.setName(name);
        promotionClientPO.setStart(start);
        promotionClientPO.setEnd(end);
        promotionClientPO.setClientLevel(clientLevel);
        promotionClientPO.setDiscount(discount);
        promotionClientPO.setVoucher(voucher);

        ArrayList<GiftItemPO> giftItemPOList=new ArrayList<>();
        for(GiftItemVO giftItemVO:giftList){
            giftItemPOList.add(giftItemVO.getGiftItemPO());
        }
        promotionClientPO.setGiftList(giftItemPOList);

        return promotionClientPO;
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
            return giftList;
        else
            return new ArrayList<>();
    }

    /*计算适用的促销策略的代金券总额*/
    public double countVoucher(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        if(client.getLevel() >= this.getClientLevel())
            return voucher;
        else
            return 0;
    }

    /*计算适用的促销策略的折让部分*/
    public double countPromotionDiscount(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        if(client.getLevel() >= this.getClientLevel())
            return discount;
        else
            return 0;
    }
}
