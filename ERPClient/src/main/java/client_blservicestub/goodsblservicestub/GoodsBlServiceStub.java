package main.java.client_blservicestub.goodsblservicestub;

import main.java.businesslogicservice.goodsblservice.GoodsBlService;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class GoodsBlServiceStub implements GoodsBlService {

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String addGoods(GoodsVO goods) throws Exception {
        return "";
    }

    @Override
    public void editGoods(GoodsVO goods) throws Exception {

    }

    @Override
    public void deleteGoods(String goodsID) throws Exception {

    }
}
