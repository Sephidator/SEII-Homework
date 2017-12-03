package main.java.businesslogic.goodsbl;

import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface GoodsTool {
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);

    public void editGoods(GoodsVO goods);
}
