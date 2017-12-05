package main.java.po.goods;

import java.io.Serializable;

public class GoodsSortQueryPO implements Serializable {
    public String name; //商品分类名称

    public GoodsSortQueryPO(String name) {
        this.name = name;
    }
}
