package businesslogic.goodssortbl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.goodssortblservice.GoodsSortBLService;
import vo.goods.GoodsSortQueryVO;
import vo.goods.GoodsSortVO;

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
