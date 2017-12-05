package main.java.vo.goods;

import main.java.po.goods.GoodsSortQueryPO;

public class GoodsSortQueryVO{
    public String name; //商品分类名称

    public GoodsSortQueryVO(String name) {
        this.name = name;
    }

    public GoodsSortQueryPO getGoodsSortQueryPO(){
        GoodsSortQueryPO goodsSortQueryPO=new GoodsSortQueryPO(this.name);

        return goodsSortQueryPO;
    }
}
