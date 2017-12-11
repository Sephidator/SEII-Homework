package main.java.vo.goods;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.po.goods.GiftItemPO;
import main.java.vo.goods.GoodsVO;

public class GiftItemVO {
    public GoodsVO goods;
    public int number;
    public double price;

    public GiftItemVO(GoodsVO goodsVO, int number, double price) {
        this.goods=goodsVO;
        this.number = number;
        this.price = price;
    }

    public GiftItemPO getGiftItemPO(){
        GiftItemPO giftItemPO=new GiftItemPO(this.goods.getID(),this.number,this.price);
        return giftItemPO;
    }

    public GiftItemVO(GiftItemPO giftItemPO)throws Exception{
        this.number=giftItemPO.number;

        GoodsTool goodsTool=new GoodsBl();
        this.goods=goodsTool.find(giftItemPO.goodsID);
    }
}
