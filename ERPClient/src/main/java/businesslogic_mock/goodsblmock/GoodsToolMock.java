package main.java.businesslogic_mock.goodsblmock;

import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class GoodsToolMock implements GoodsTool {

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public void editGoods(GoodsVO goods) throws Exception {

    }

    @Override
    public GoodsVO find(String goodsID) throws Exception {
        return new GoodsVO();
    }
}
