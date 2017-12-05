package main.java.po.goods;

import java.io.Serializable;

public class GoodsQueryPO implements Serializable {
    public String name; //商品名称
    public String goodsSortID;//商品所在商品分类

    public GoodsQueryPO(String name, String goodsSortID) {
        this.name = name;
        this.goodsSortID = goodsSortID;
    }
}
