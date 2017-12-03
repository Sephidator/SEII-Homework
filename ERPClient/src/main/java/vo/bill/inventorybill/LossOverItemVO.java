package main.java.vo.bill.inventorybill;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.po.bill.inventorybill.LossOverItemPO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

public class LossOverItemVO {
    public GoodsVO goods;// 报溢损的商品
    public double price;//商品的单价
    public int goodsNumber;// 商品在系统中的数量
    public int actualNumber;//商品的实际数量

    public LossOverItemVO(GoodsVO goods, double price, int goodsNumber, int actualNumber) {
        this.goods = goods;
        this.price = price;
        this.goodsNumber = goodsNumber;
        this.actualNumber = actualNumber;
    }

    public LossOverItemPO getLossOverItemPO(){
        LossOverItemPO lossOverItemPO=new LossOverItemPO(this.goods.getID(),this.price,this.goodsNumber,this.actualNumber);
        return lossOverItemPO;
    }

    public LossOverItemVO(LossOverItemPO lossOverItemPO){
        this.price=lossOverItemPO.price;
        this.goodsNumber=lossOverItemPO.goodsNumber;
        this.actualNumber=lossOverItemPO.actualNumber;

        GoodsTool goodsTool=new GoodsBl();
        GoodsQueryVO goodsQueryVO=new GoodsQueryVO(lossOverItemPO.goodsID,null);
        this.goods=goodsTool.getGoodsList(goodsQueryVO).get(0);
    }


}
