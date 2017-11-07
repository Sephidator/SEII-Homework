package businesslogic_mocktester.goodsblmocktester;

import businesslogic.blutility.ResultMessage;
import businesslogic.goodsbl.GoodsTool;
import businesslogic_mock.goodsblmock.GoodsToolMock;

import static org.junit.Assert.*;

public class GoodsToolMockTester {

    GoodsTool goodsTool=new GoodsToolMock();

    @org.junit.Test
    public void getGoodsList() throws Exception {
        assertEquals(1,goodsTool.getGoodsList(null).size());
    }

    @org.junit.Test
    public void editGoods() throws Exception {
        assertEquals(ResultMessage.success,goodsTool.editGoods(null));
    }

}