package po;

import java.util.HashMap;
import java.util.Date;

//组合特价包的促销策略对象
public class PromotionGoodsPO extends PromotionPO {
    //商品编号及数量
    private HashMap<String, Integer> goods = new HashMap<>();
    //总价
    private double total;

    public PromotionGoodsPO(String ID, int type, Date start, Date end, HashMap<String, Integer> goods, int total) {
        this.ID = ID;
        this.type = type;
        this.start = start;
        this.end = end;
        this.goods = goods;
        this.total = total;
    }

    public HashMap<String, Integer> getGoods() {
        return goods;
    }

    public double total() {
        return total;
    }
}
