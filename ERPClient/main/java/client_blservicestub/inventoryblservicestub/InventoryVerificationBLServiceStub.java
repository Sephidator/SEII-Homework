package client_blservicestub.inventoryblservicestub;

import businesslogicservice.inventoryblservice.InventoryVerificationBLService;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryVerificationBLServiceStub implements InventoryVerificationBLService {
    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        ArrayList<GoodsVO> goodsVOS=new ArrayList<GoodsVO>();
        goodsVOS.add(null);
        return goodsVOS;
    }
}
