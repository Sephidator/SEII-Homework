package main.java.vo.promotion;

import main.java.vo.client.ClientVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.goods.GoodsItemVO;

import java.util.ArrayList;
import java.util.Date;

public class PromotionTotalVO extends PromotionVO {

    private double total;//总价

    private double voucher;//代金券

    private ArrayList<GiftItemVO> giftList; // 赠品列表

    public PromotionTotalVO(){
        this.start=new Date();
        this.end=new Date();
        this.total=0;
        this.voucher=0;
        this.giftList=new ArrayList<>();
    }

    public PromotionTotalVO(Date start, Date end, double total, double voucher, ArrayList<GiftItemVO> giftList) {
        this.type="总价促销策略";
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

    /*计算适用的促销策略的赠品列表*/
    public ArrayList<GiftItemVO> countGiftList(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        if(total >= this.getTotal())
            return this.getGiftList();
        else
            return new ArrayList<>();
    }

    /*计算适用的促销策略的代金券总额*/
    public double countVoucher(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        if(total >= this.getTotal())
            return this.getVoucher();
        else
            return 0;
    }

    /*计算适用的促销策略的折让部分*/
    public double countPromotionDiscount(ArrayList<GoodsItemVO> goodsItemList, ClientVO client, double total){
        return 0;
    }
}
