package main.java.vo.promotion;

import main.java.vo.goods.GoodsItemVO;

import java.util.ArrayList;
import java.util.Date;

public class PromotionGoodsVO extends PromotionVO {

    private ArrayList<GoodsItemVO> goodsList; //组合降价的商品列表

    private double total; //总价

    PromotionGoodsVO(){

    }

    public PromotionGoodsVO(String ID, String type, Date start, Date end, ArrayList<GoodsItemVO> goodsList, double total) {
        this.ID=ID;
        this.type=type;
        this.start=start;
        this.end=end;
        this.goodsList = goodsList;
        this.total = total;
    }

    public ArrayList<GoodsItemVO> getGoodsList() {
        return goodsList;
    }

    public double getTotal() {
        return total;
    }

    public void setGoodsList(ArrayList<GoodsItemVO> goodsList) {
        this.goodsList = goodsList;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
