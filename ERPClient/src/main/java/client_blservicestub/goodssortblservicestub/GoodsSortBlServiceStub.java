package main.java.client_blservicestub.goodssortblservicestub;

import main.java.businesslogicservice.goodssortblservice.GoodsSortBlService;
import main.java.vo.goods.GoodsSortVO;

public class GoodsSortBlServiceStub implements GoodsSortBlService {

    @Override
    public GoodsSortVO getRoot() throws Exception {
        return new GoodsSortVO();
    }

    @Override
    public GoodsSortVO find(String goodsSortID) throws Exception {
        return new GoodsSortVO();
    }

    @Override
    public String addGoodsSort(GoodsSortVO goodsSort) throws Exception {
        return "";
    }

    @Override
    public void deleteGoodsSort(String goodsSortID) throws Exception {

    }

    @Override
    public void editGoodsSort(GoodsSortVO goodsSort) throws Exception {

    }
}
