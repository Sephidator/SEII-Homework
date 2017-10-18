package po;

import java.util.HashMap;

import java.util.Date;

//针对不同级别用户的促销策略对象
public class PromotionClientPO extends PromotionPO {
    //客户等级
    private int clientLevel;
//代金券
    private int voucher;
//折让
    private double discount;
   //赠品编号及数量
    private HashMap<String, Integer> gift = new HashMap();

    public PromotionClientPO(int id, Date start, Date end, int clientLevel, int voucher, double discount, HashMap<String, Integer> gift) {
        super(id, start, end);
        this.clientLevel = clientLevel;
        this.voucher = voucher;
        this.discount = discount;
        this.gift = gift;
    }

    public int getClientLevel() {
        return clientLevel;
    }

    public int getVoucher() {
        return voucher;
    }

    public double getDiscount() {
        return discount;
    }

    public HashMap<String, Integer> getGift() {
        return gift;
    }
}
