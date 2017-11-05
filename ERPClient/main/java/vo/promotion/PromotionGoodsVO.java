package vo.promotion;

import java.util.Date;
import java.util.HashMap;

public class PromotionGoodsVO extends PromotionVO {
    //商品编号及数量
    public HashMap<String, Integer> goods = new HashMap<>();
    //总价
    public double total;

    public PromotionGoodsVO(String ID, int type, Date start, Date end, HashMap<String, Integer> goods, int total) {
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

    public double getTotal() {
        return total;
    }
}
