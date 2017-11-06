package vo.promotion;

import java.util.ArrayList;
import java.util.Date;

public class PromotionTotalVO extends PromotionVO {

    private double total;//总价

    private double voucher;//代金券

    private ArrayList<GiftItemVO> giftList; // 赠品列表

    public PromotionTotalVO(){

    }

    public PromotionTotalVO(String ID,String type, Date start, Date end, double total, double voucher, ArrayList<GiftItemVO> giftList) {
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

    public ArrayList<GiftItemVO> getGiftList() {
        return giftList;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
    }

    public void setGiftList(ArrayList<GiftItemVO> giftList) {
        this.giftList = giftList;
    }
}
