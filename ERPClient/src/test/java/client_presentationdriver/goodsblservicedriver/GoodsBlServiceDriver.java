package test.java.client_presentationdriver.goodsblservicedriver;

import main.java.businesslogicservice.goodsblservice.GoodsBlService;
import main.java.client_blservicestub.goodsblservicestub.GoodsBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsBlServiceDriver {
    GoodsBlService service=new GoodsBlServiceStub();

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(0,service.getGoodsList(null).size());
    }

    @Test
    public void addGoods() throws Exception {
        assertEquals("",service.addGoods(null));
    }

    @Test
    public void editGoods() throws Exception {
    }

    @Test
    public void deleteGoods() throws Exception {
    }

}