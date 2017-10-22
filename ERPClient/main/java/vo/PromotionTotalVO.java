package vo;

import java.util.Date;
import java.util.HashMap;

public class PromotionTotalVO extends PromotionVO {
    //总价
    public double total;
    //代金券
    public double voucher;
    //赠品编号及数量
    public HashMap<String, Integer> gift = new HashMap<>();

    public PromotionTotalVO(String ID, int type, Date start, Date end, int total, int voucher, HashMap<String, Integer> gift) {
        this.ID = ID;
        this.type = type;
        this.start = start;
        this.end = end;
        this.total = total;
        this.voucher = voucher;
        this.gift = gift;
    }
}
