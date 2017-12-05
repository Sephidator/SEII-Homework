package main.java.po.promotion;

import main.java.po.goods.GoodsItemPO;

import java.util.ArrayList;
import java.util.Date;

public class PromotionGoodsPO extends PromotionPO {

    private double discount; //降价折让部分

    private ArrayList<GoodsItemPO> goodsList; //组合降价的商品列表

    public PromotionGoodsPO() {

    }

    public PromotionGoodsPO(String name, Date start, Date end, double discount, ArrayList<GoodsItemPO> goodsList) {
        this.name = name;
        type = "特价包";
        this.start = start;
        this.end = end;
        this.discount = discount;
        this.goodsList = goodsList;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public ArrayList<GoodsItemPO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ArrayList<GoodsItemPO> goodsList) {
        this.goodsList = goodsList;
    }

}
