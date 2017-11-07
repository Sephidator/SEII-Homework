package businesslogic.goodsbl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.goodsblservice.GoodsBLService;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public class GoodsBl implements GoodsBLService,GoodsTool {
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage addGoods(GoodsVO goods) {
        return null;
    }

    @Override
    public ResultMessage editGoods(GoodsVO goods) {
        return null;
    }

    @Override
    public ResultMessage deleteGoods(GoodsVO goods) {
        return null;
    }

    @Override
    public String getGoodsID() {
        return null;
    }
}
