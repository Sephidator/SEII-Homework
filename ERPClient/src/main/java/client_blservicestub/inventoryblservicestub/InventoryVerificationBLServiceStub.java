package main.java.client_blservicestub.inventoryblservicestub;

import main.java.businesslogicservice.inventoryblservice.InventoryVerificationBLService;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryVerificationBLServiceStub implements InventoryVerificationBLService {
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        ArrayList<GoodsVO> goodsVOS=new ArrayList<GoodsVO>();
        goodsVOS.add(null);
        return goodsVOS;
    }
}
