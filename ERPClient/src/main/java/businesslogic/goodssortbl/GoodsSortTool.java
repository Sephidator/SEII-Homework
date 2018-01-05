package main.java.businesslogic.goodssortbl;

import main.java.vo.goods.GoodsSortVO;

public interface GoodsSortTool {

    GoodsSortVO find(String goodsSortID) throws Exception;//通过goodsSortID查找某个商品分类
}
