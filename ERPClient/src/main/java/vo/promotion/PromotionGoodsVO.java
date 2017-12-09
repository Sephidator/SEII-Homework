package main.java.vo.promotion;

import main.java.vo.goods.GoodsItemVO;

import java.util.ArrayList;
import java.util.Date;

public class PromotionGoodsVO extends PromotionVO {

    private ArrayList<GoodsItemVO> goodsList; //组合降价的商品列表

    private double discount; //降价折让部分

    PromotionGoodsVO(){
        this.name="";
        this.type="特价包";
        this.start=new Date();
        this.end=new Date();
        this.goodsList = new ArrayList<>();
        this.discount = 0;
    }

    public PromotionGoodsVO(String name, Date start, Date end, double discount,ArrayList<GoodsItemVO> goodsList) {
        this.name=name;
        this.type="特价包";
        this.start=start;
        this.end=end;
        this.goodsList = goodsList;
        this.discount = discount;
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
}
