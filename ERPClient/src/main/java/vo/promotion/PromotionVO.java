package main.java.vo.promotion;

import main.java.po.promotion.PromotionPO;
import main.java.presentation.uiutility.InfoUIController;
import main.java.vo.VO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsItemVO;

import java.util.ArrayList;
import java.util.Date;

public class PromotionVO extends VO {

    protected String name;//促销策略名字

    protected String type;//促销策略类型

    protected Date start;//促销策略起始时间

    protected Date end;//促销策略截止时间

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public PromotionVO(String name, String type, Date start, Date end){
        this.name = name;
        this.type = type;
        this.start = start;
        this.end = end;
    }
    public PromotionVO(){
        this.name = "";
        this.type = "";
        this.start = new Date();
        this.end = new Date();
    }
    /*从PromotionPO转至PromotionVO*/
    public PromotionVO(PromotionPO promotionPO){
        this.setID(promotionPO.getID());
        this.setName(promotionPO.getName());
        this.setEnd(promotionPO.getEnd());
        this.setStart(promotionPO.getStart());
        this.setType(promotionPO.getType());
    }

    /*从PromotionVO转至PromotionPO*/
    public PromotionPO getPromotionPO(){
        PromotionPO promotionPO = new PromotionPO();
        promotionPO.setID(this.getID());
        promotionPO.setName(this.getName());
        promotionPO.setType(this.getType());
        promotionPO.setStart(this.getStart());
        promotionPO.setEnd(this.getEnd());

        return promotionPO;
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
        return 0;
    }

    /*实现待定*/
    public InfoUIController getInfoUIController() {
        return null;
    }
}
