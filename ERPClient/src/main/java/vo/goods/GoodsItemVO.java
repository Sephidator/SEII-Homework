package main.java.vo.goods;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.po.goods.GoodsItemPO;

public class GoodsItemVO {
    public GoodsVO goods;
    public int number;
    public double price;//商品单价

    public GoodsItemVO(GoodsVO goodsVO, int number,double price) {
        this.goods=goodsVO;
        this.number = number;
        this.price=price;
    }

    public GoodsItemPO getGoodsItemPO(){
        GoodsItemPO goodsItemPO=new GoodsItemPO(this.goods.getID(),this.number,this.price);
        return goodsItemPO;
    }

    public GoodsItemVO(GoodsItemPO goodsItemPO)throws Exception{
        this.number=goodsItemPO.number;

        GoodsTool goodsTool=new GoodsBl();
        this.goods=goodsTool.find(goodsItemPO.goodsID);

        this.price=goodsItemPO.price;
    }
}