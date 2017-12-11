package main.java.businesslogic.goodssortbl;

import main.java.vo.goods.GoodsSortVO;

public interface GoodsSortTool {

    public GoodsSortVO find(String goodsSortID);//通过goodsSortID查找某个商品分类
}
