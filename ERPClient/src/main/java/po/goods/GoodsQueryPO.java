package main.java.po.goods;

import main.java.po.QueryPO;

public class GoodsQueryPO extends QueryPO {
    public String name; //商品名称

    public GoodsQueryPO(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
}
