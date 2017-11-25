package main.java.businesslogic.goodssortbl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.goodssortblservice.GoodsSortBLService;
import main.java.vo.goods.GoodsSortQueryVO;
import main.java.vo.goods.GoodsSortVO;

import java.util.ArrayList;

public class GoodsSortBl implements GoodsSortBLService,GoodsSortTool {
    @Override
    public ArrayList<GoodsSortVO> getGoodsSortList(GoodsSortQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage addGoodsSort(GoodsSortVO goodsSort) {
        return null;
    }

    @Override
    public ResultMessage deleteGoodsSort(GoodsSortVO goodsSort) {
        return null;
    }

    @Override
    public ResultMessage editGoodsSort(GoodsSortVO goodsSort) {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }
}
