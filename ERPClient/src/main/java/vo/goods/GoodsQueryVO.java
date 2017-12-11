package main.java.vo.goods;

import main.java.po.goods.GoodsQueryPO;


public class GoodsQueryVO{
    public String name; //商品名称
    public String goodsID;//商品所在商品分类

    public GoodsQueryVO(String name,String goodsID) {
        this.name = name;
        this.goodsID = goodsID;
    }

    public GoodsQueryPO getGoodsQueryPO(){
        GoodsQueryPO goodsQueryPO=new GoodsQueryPO(this.name,this.goodsID);
        return goodsQueryPO;
    }
}
