package test.java.unit_test.goodsbltest;

import main.java.businesslogicfactory.goodsblfactory.GoodsBlFactory;
import main.java.businesslogicservice.goodsblservice.GoodsBlService;
import main.java.client_blservicestub.goodsblservicestub.GoodsBlServiceStub;
import main.java.vo.goods.GoodsVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoodsBlServiceTest {
    GoodsBlService service= GoodsBlFactory.getService();

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(true,service.getGoodsList(null).size()>=0);
    }

    @Test
    public void addGoods() throws Exception {
        assertEquals("Goods",service.addGoods(new GoodsVO()).substring(0,4));
    }

}