package main.java.vo.goods;

import main.java.po.goods.GoodsQueryPO;
import main.java.vo.QueryVO;


public class GoodsQueryVO extends QueryVO{
    public String name; //商品名称

    public GoodsQueryVO(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public GoodsQueryPO getGoodsQueryPO(){
        GoodsQueryPO goodsQueryPO=new GoodsQueryPO(this.ID,this.name);
        return goodsQueryPO;
    }
}
