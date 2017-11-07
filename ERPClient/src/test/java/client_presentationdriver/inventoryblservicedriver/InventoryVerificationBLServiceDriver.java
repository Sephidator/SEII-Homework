package client_presentationdriver.inventoryblservicedriver;

import businesslogicservice.inventoryblservice.InventoryVerificationBLService;
import client_blservicestub.inventoryblservicestub.InventoryVerificationBLServiceStub;
import org.junit.Test;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class InventoryVerificationBLServiceDriver {

    InventoryVerificationBLService inventoryVerificationBLService=new InventoryVerificationBLServiceStub();
    GoodsQueryVO goodsQueryVO=new GoodsQueryVO();

    @Test
    public void getGoodsList() throws Exception {
        ArrayList<GoodsVO> goodsVOS=new ArrayList<GoodsVO>();
        goodsVOS.add(null);
        assertEquals(goodsVOS,inventoryVerificationBLService.getGoodsList(goodsQueryVO));
    }

}