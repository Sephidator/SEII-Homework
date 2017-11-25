package main.java.client_blservicestub.goodssortblservicestub;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.goodssortblservice.GoodsSortBLService;
import main.java.vo.goods.GoodsSortQueryVO;
import main.java.vo.goods.GoodsSortVO;

import java.util.ArrayList;

public class GoodsSortBLServiceStub implements GoodsSortBLService {
    @Override
    public ArrayList<GoodsSortVO> getGoodsSortList(GoodsSortQueryVO query) {
        ArrayList<GoodsSortVO> goodsSortVO=new ArrayList<GoodsSortVO>();
        goodsSortVO.add(null);
        return goodsSortVO;
    }

    @Override
    public ResultMessage addGoodsSort(GoodsSortVO goodsSort) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage deleteGoodsSort(GoodsSortVO goodsSort) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage editGoodsSort(GoodsSortVO goodsSort) {
        return ResultMessage.success;
    }

    @Override
    public String getID() {
        return "C0002";
    }
}
