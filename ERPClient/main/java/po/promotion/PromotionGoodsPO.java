package po.promotion;

import java.util.ArrayList;
import java.util.Date;

public class PromotionGoodsPO extends PromotionPO {

    private ArrayList<GoodsItemPO> goodsList; //组合降价的商品列表

    private double total; //总价

    PromotionGoodsPO(){

    }

    public PromotionGoodsPO(String ID, String type, Date start, Date end, ArrayList<GoodsItemPO> goodsList, double total) {
        this.ID=ID;
        this.type=type;
        this.start=start;
        this.end=end;
        this.goodsList = goodsList;
        this.total = total;
    }

    public ArrayList<GoodsItemPO> getGoodsList() {
        return goodsList;
    }

    public double getTotal() {
        return total;
    }

    public void setGoodsList(ArrayList<GoodsItemPO> goodsList) {
        this.goodsList = goodsList;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
