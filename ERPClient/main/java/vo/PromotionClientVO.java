package vo;

import java.util.Date;
import java.util.HashMap;

public class PromotionClientVO extends PromotionVO {
    private int clientLevel;
    //代金券
    private double voucher;
    //折让
    private double discount;
    //赠品编号及数量
    private HashMap<String, Integer> gift = new HashMap<>();

    public PromotionClientVO(String ID, int type, Date start, Date end, int clientLevel, int voucher, double discount, HashMap<String, Integer> gift) {
        this.clientLevel = clientLevel;
        this.voucher = voucher;
        this.discount = discount;
        this.gift = gift;
    }
}
