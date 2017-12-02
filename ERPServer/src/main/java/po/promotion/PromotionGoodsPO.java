package main.java.po.promotion;

import main.java.po.goods.GoodsItemPO;

import java.util.ArrayList;
import java.util.Date;

public class PromotionGoodsPO extends PromotionPO {

    private double total; //总价

    private ArrayList<GoodsItemPO> goodsList; //组合降价的商品列表

    public PromotionGoodsPO() {

    }

    public PromotionGoodsPO(String name, Date start, Date end, double total, ArrayList<GoodsItemPO> goodsList) {
        this.name = name;
        type = "特价包";
        this.start = start;
        this.end = end;
        this.total = total;
        this.goodsList = goodsList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<GoodsItemPO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ArrayList<GoodsItemPO> goodsList) {
        this.goodsList = goodsList;
    }

}
