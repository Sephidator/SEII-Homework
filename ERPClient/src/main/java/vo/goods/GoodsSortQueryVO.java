package main.java.vo.goods;

import main.java.po.goods.GoodsSortQueryPO;
import main.java.vo.QueryVO;

public class GoodsSortQueryVO extends QueryVO{
    public String name; //商品分类名称
    public String goodsSortID;//商品所在商品分类

    public GoodsSortQueryVO(String ID, String name, String goodsSortID) {
        this.ID = ID;
        this.name = name;
        this.goodsSortID = goodsSortID;
    }

    public GoodsSortQueryPO getGoodsSortQueryPO(){
        GoodsSortQueryPO goodsSortQueryPO=new GoodsSortQueryPO(this.ID,this.name,this.goodsSortID);

        return goodsSortQueryPO;
    }
}
