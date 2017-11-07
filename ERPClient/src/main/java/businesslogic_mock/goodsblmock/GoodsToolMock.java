package businesslogic_mock.goodsblmock;

import businesslogic.blutility.ResultMessage;
import businesslogic.goodsbl.GoodsTool;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

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
