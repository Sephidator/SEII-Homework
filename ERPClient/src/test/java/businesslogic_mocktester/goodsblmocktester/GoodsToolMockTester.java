package test.java.businesslogic_mocktester.goodsblmocktester;

import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogic_mock.goodsblmock.GoodsToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsToolMockTester {
    GoodsTool tool=new GoodsToolMock();

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(0,tool.getGoodsList(null).size());
    }

    @Test
    public void editGoods() throws Exception {
    }

    @Test
    public void find() throws Exception {
        assertEquals("",tool.find(null).getID());
    }

}