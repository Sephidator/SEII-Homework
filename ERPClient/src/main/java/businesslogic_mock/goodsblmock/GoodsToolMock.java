package main.java.businesslogic_mock.goodsblmock;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class GoodsToolMock implements GoodsTool {
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        ArrayList<GoodsVO> goodsVOS=new ArrayList<>();
        goodsVOS.add(new GoodsVO());
        return goodsVOS;
    }

    @Override
    public ResultMessage editGoods(GoodsVO goods) {
        return ResultMessage.success;
    }
}
