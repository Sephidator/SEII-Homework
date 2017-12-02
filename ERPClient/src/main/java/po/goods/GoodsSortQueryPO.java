package main.java.po.goods;

import main.java.po.QueryPO;

public class GoodsSortQueryPO extends QueryPO {
    public String name; //商品分类名称
    public String goodsSortID;//商品所在商品分类

    public GoodsSortQueryPO(String ID, String name, String goodsSortID) {
        this.ID = ID;
        this.name = name;
        this.goodsSortID = goodsSortID;
    }
}
