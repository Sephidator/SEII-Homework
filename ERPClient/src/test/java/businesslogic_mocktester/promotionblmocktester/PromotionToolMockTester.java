package test.java.businesslogic_mocktester.promotionblmocktester;

import main.java.businesslogic.promotionbl.PromotionTool;
import main.java.businesslogic_mock.promotionblmock.PromotionToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class PromotionToolMockTester {
    PromotionTool tool=new PromotionToolMock();

    @Test
    public void getPromotionList() throws Exception {
        assertEquals(0,tool.getPromotionList(null).size());
    }

    @Test
    public void find() throws Exception {
        assertEquals("",tool.find(null).getID());
    }

}