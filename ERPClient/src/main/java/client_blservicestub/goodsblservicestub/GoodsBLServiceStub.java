package client_blservicestub.goodsblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.goodsblservice.GoodsBLService;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public class GoodsBLServiceStub implements GoodsBLService {
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        ArrayList<GoodsVO> goods=new ArrayList<GoodsVO>();
        goods.add(null);
        return goods;
    }

    @Override
    public ResultMessage addGoods(GoodsVO goods) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage editGoods(GoodsVO goods) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage deleteGoods(GoodsVO goods) {
        return ResultMessage.success;
    }

    @Override
    public String getGoodsID() {
        return "C0002";
    }
}
