package main.java.po.goods;

import java.io.Serializable;

public class GoodsQueryPO implements Serializable {
    public String name; //商品名称
    public String goodsID;//商品编号

    public GoodsQueryPO(String name, String goodsID) {
        this.name = name;
        this.goodsID = goodsID;
    }
}
