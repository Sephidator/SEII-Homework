package po;

import java.util.Date;
import java.util.HashMap;

//针对不同总价的促销策略对象
public class PromotionTotalPO extends PromotionPO {
    //总价
    private int total;
    //代金券
    private int voucher;
    //赠品编号及数量
    private HashMap<String, Integer> gift = new HashMap();

    public PromotionTotalPO(int id, Date start, Date end, int total, int voucher, HashMap<String, Integer> gift) {
        super(id, start, end);
        this.total = total;
        this.voucher = voucher;
        this.gift = gift;
    }

    public int getTotal() {
        return total;
    }

    public int getVoucher() {
        return voucher;
    }

    public HashMap<String, Integer> getGift() {
        return gift;
    }
}
