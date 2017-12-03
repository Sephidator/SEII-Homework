package main.java.vo.goods;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.po.goods.GiftItemPO;
import main.java.vo.goods.GoodsVO;

public class GiftItemVO {
    public GoodsVO goods;
    public int number;

    public GiftItemVO(GoodsVO goodsVO, int number) {
        this.goods=goodsVO;
        this.number = number;
    }

    public GiftItemPO getGiftItemPO(){
        GiftItemPO giftItemPO=new GiftItemPO(this.goods.getID(),this.number);
        return giftItemPO;
    }

    public GiftItemVO(GiftItemPO giftItemPO){
        this.number=giftItemPO.number;

        GoodsTool goodsTool=new GoodsBl();
        GoodsQueryVO goodsQueryVO=new GoodsQueryVO(giftItemPO.goodsID,null);
        this.goods=goodsTool.getGoodsList(goodsQueryVO).get(0);
    }
}
