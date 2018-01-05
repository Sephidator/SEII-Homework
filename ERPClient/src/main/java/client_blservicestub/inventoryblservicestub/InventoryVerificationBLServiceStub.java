package main.java.client_blservicestub.inventoryblservicestub;

import main.java.businesslogicservice.inventoryblservice.InventoryVerificationBlService;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryVerificationBlServiceStub implements InventoryVerificationBlService {

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        return new ArrayList<>();
    }
}
