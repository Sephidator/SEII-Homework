package test.java.client_presentationdriver.inventoryblservicedriver;

import main.java.businesslogicservice.inventoryblservice.InventoryVerificationBLService;
import main.java.client_blservicestub.inventoryblservicestub.InventoryVerificationBLServiceStub;
import org.junit.Test;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

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