package main.java.po.goods;

import main.java.po.QueryPO;

public class GoodsQueryPO extends QueryPO {
    public String name; //商品名称
    public String goodsSortID;//商品所在商品分类

    public GoodsQueryPO(String ID, String name, String goodsSortID) {
        this.ID = ID;
        this.name = name;
        this.goodsSortID = goodsSortID;
    }
}
