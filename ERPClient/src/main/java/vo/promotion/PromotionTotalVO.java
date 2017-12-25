package main.java.vo.promotion;

import main.java.po.goods.GiftItemPO;
import main.java.po.promotion.PromotionTotalPO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsItemVO;

import java.util.ArrayList;
import java.util.Date;

public class PromotionTotalVO extends PromotionVO {

    private double total;//总价

    private double voucher;//代金券

    private ArrayList<GiftItemVO> giftList; // 赠品列表

    public PromotionTotalVO(){
        this.name="";
        this.type="总价促销策略";
        this.start=new Date();
        this.end=new Date();
        this.total=0;
        this.voucher=0;
        this.giftList=new ArrayList<>();
    }

    public PromotionTotalVO(PromotionTotalPO promotionTotalPO) throws Exception{
        this.ID=promotionTotalPO.getID();
        this.name=promotionTotalPO.getName();
        this.type="总价促销策略";
        this.start=promotionTotalPO.getStart();
        this.end=promotionTotalPO.getEnd();
        this.total=promotionTotalPO.getTotal();
        this.voucher=promotionTotalPO.getVoucher();

        this.giftList=new ArrayList<>();
        for(GiftItemPO giftItemPO:promotionTotalPO.getGiftList()){
            this.giftList.add(new GiftItemVO(giftItemPO));
        }
    }

    @Override
    public PromotionTotalPO getPromotionPO(){
        PromotionTotalPO promotionTotalPO=new PromotionTotalPO();

        promotionTotalPO.setID(ID);
        promotionTotalPO.setType("总价促销策略");
        promotionTotalPO.setName(name);
        promotionTotalPO.setStart(start);
        promotionTotalPO.setEnd(end);
        promotionTotalPO.setTotal(total);
        promotionTotalPO.setVoucher(voucher);

        ArrayList<GiftItemPO> giftItemPOList=new ArrayList<>();
        for(GiftItemVO giftItemVO:giftList){
            giftItemPOList.add(giftItemVO.getGiftItemPO());
        }
        promotionTotalPO.setGiftList(giftItemPOList);

        return promotionTotalPO;
    }
    
    public double getTotal() {
        return total;
    }

    public double getVoucher() {
        return voucher;
    }

    public ArrayList<GiftItemVO> getGiftList() {
        return giftList;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
    }

    public void setGiftList(ArrayList<GiftItemVO> giftList) {
        this.giftList = giftList;
    }

    /*计算适用的促销策略的赠品列表*/
    public ArrayList<GiftItemVO> countGiftList(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        if(total >= this.getTotal())
            return this.getGiftList();
        else
            return new ArrayList<>();
    }

    /*计算适用的促销策略的代金券总额*/
    public double countVoucher(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        if(total >= this.getTotal())
            return this.getVoucher();
        else
            return 0;
    }

    /*计算适用的促销策略的折让部分*/
    public double countPromotionDiscount(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        return 0;
    }
}
