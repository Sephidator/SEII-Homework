package main.java.vo.goods;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.po.goods.GoodsItemPO;
import main.java.vo.goods.GoodsVO;

public class GoodsItemVO {
    public GoodsVO goods;
    public int number;
    public double price;//商品单价

    public GoodsItemVO(GoodsVO goodsVO, int number) {
        this.goods=goodsVO;
        this.number = number;
        price=goodsVO.getCost();
    }

    public GoodsItemPO getGoodsItemPO(){
        GoodsItemPO goodsItemPO=new GoodsItemPO(this.goods.getID(),this.number,this.price);
        return goodsItemPO;
    }

    public GoodsItemVO(GoodsItemPO goodsItemPO){
        this.number=goodsItemPO.number;

        GoodsTool goodsTool=new GoodsBl();
        GoodsQueryVO goodsQueryVO=new GoodsQueryVO(goodsItemPO.goodsID,null);
        this.goods=goodsTool.getGoodsList(goodsQueryVO).get(0);
    }
}