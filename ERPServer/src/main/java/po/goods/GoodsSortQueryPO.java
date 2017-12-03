package main.java.po.goods;

import main.java.po.QueryPO;

public class GoodsSortQueryPO extends QueryPO {
    public String name; //商品分类名称

    public GoodsSortQueryPO(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
}
