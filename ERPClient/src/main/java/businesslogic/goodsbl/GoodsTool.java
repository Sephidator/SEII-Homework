package main.java.businesslogic.goodsbl;

import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface GoodsTool {
    ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    void editGoods(GoodsVO goods) throws Exception;

    GoodsVO find(String goodsID) throws Exception;//通过goodsID查找某个商品
}
