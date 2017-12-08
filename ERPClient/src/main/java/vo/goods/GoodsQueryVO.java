package main.java.vo.goods;

import main.java.po.goods.GoodsQueryPO;


public class GoodsQueryVO{
    public String name; //商品名称
    public String goodsSortID;//商品所在商品分类

    public GoodsQueryVO(String name,String goodsSortID) {
        this.name = name;
        this.goodsSortID = goodsSortID;
    }

    public GoodsQueryPO getGoodsQueryPO(){
        GoodsQueryPO goodsQueryPO=new GoodsQueryPO(this.name,this.goodsSortID);
        return goodsQueryPO;
    }
}
