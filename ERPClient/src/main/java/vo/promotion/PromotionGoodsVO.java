package main.java.vo.promotion;

import main.java.businesslogic.blutility.Arith;
import main.java.po.goods.GoodsItemPO;
import main.java.po.promotion.PromotionGoodsPO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsItemVO;

import java.util.ArrayList;
import java.util.Date;

public class PromotionGoodsVO extends PromotionVO {

    private ArrayList<GoodsItemVO> goodsList; //组合降价的商品列表

    private double discount; //降价折让部分

    public PromotionGoodsVO(){
        this.name="";
        this.type="商品促销策略";
        this.start=new Date();
        this.end=new Date();
        this.goodsList = new ArrayList<>();
        this.discount = 0;
    }

    public PromotionGoodsVO(PromotionGoodsPO promotionGoodsPO) throws Exception{
        this.ID=promotionGoodsPO.getID();
        this.name=promotionGoodsPO.getName();
        this.type="商品促销策略";
        this.start=promotionGoodsPO.getStart();
        this.end=promotionGoodsPO.getEnd();
        this.discount = promotionGoodsPO.getDiscount();

        this.goodsList=new ArrayList<>();
        for(GoodsItemPO goodsItemPO:promotionGoodsPO.getGoodsList()){
            goodsList.add(new GoodsItemVO(goodsItemPO));
        }
    }

    @Override
    public PromotionGoodsPO getPromotionPO(){
        PromotionGoodsPO promotionGoodsPO=new PromotionGoodsPO();

        promotionGoodsPO.setID(ID);
        promotionGoodsPO.setName(name);
        promotionGoodsPO.setType("商品促销策略");
        promotionGoodsPO.setStart(start);
        promotionGoodsPO.setEnd(end);
        promotionGoodsPO.setDiscount(discount);

        ArrayList<GoodsItemPO> goodsItemPOList=new ArrayList<>();
        for(GoodsItemVO goodsItemVO:goodsList){
            goodsItemPOList.add(goodsItemVO.getGoodsItemPO());
        }
        promotionGoodsPO.setGoodsList(goodsItemPOList);

        return promotionGoodsPO;
    }

    public ArrayList<GoodsItemVO> getGoodsList() {
        return goodsList;
    }

    public double getDiscount() {
        return discount;
    }

    public void setGoodsList(ArrayList<GoodsItemVO> goodsList) {
        this.goodsList = goodsList;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /*计算适用的促销策略的赠品列表*/
    public ArrayList<GiftItemVO> countGiftList(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        return new ArrayList<>();
    }

    /*计算适用的促销策略的代金券总额*/
    public double countVoucher(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        return 0;
    }

    /*计算适用的促销策略的折让部分*/
    public double countPromotionDiscount(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        int countGroups = 0; // 计算购买的商品中有几组组合降价商品

        for(GoodsItemVO goodsInPromotion : this.goodsList){
            boolean exist=false;
            for(GoodsItemVO goodsItem : goodsItemList){
                if((goodsInPromotion.goods.getName().equals(goodsItem.goods.getName()))
                        &&(goodsInPromotion.number<=goodsItem.number)){
                    exist=true;
                    int groupNumber=goodsItem.number/goodsInPromotion.number;
                    countGroups = (countGroups==0) ? groupNumber : Math.min(countGroups,groupNumber);
                }
            }
            if(!exist){
                return 0;
            }
        }
        return Arith.mul(countGroups, this.discount);
    }
}
