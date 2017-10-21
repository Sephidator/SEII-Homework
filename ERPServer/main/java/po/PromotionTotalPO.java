package po;

import java.util.Date;
import java.util.HashMap;

//针对不同总价的促销策略对象
public class PromotionTotalPO extends PromotionPO {
    //总价
    private double total;
    //代金券
    private double voucher;
    //赠品编号及数量
    private HashMap<String, Integer> gift = new HashMap<>();

    public PromotionTotalPO(String ID, int type, Date start, Date end, int total, int voucher, HashMap<String, Integer> gift) {
        this.ID = ID;
        this.type = type;
        this.start = start;
        this.end = end;
        this.total = total;
        this.voucher = voucher;
        this.gift = gift;
    }

    public double getTotal() {
        return total;
    }

    public double getVoucher() {
        return voucher;
    }

    public HashMap<String, Integer> getGift() {
        return gift;
    }
}
