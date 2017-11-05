package po.promotion;

import java.util.ArrayList;
import java.util.Date;

public class PromotionTotalPO extends PromotionPO {

    public double total;//总价

    public double voucher;//代金券

    private ArrayList<GiftItemPO> giftList; // 赠品列表

    public PromotionTotalPO(){

    }

    public PromotionTotalPO(String ID, String type, Date start, Date end, double total, double voucher, ArrayList<GiftItemPO> giftList) {
        this.ID=ID;
        this.type=type;
        this.start=start;
        this.end=end;
        this.total=total;
        this.voucher=voucher;
        this.giftList=giftList;
    }

    public double getTotal() {
        return total;
    }

    public double getVoucher() {
        return voucher;
    }

    public ArrayList<GiftItemPO> getGiftList() {
        return giftList;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
    }

    public void setGiftList(ArrayList<GiftItemPO> giftList) {
        this.giftList = giftList;
    }
}
