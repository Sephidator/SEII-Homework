package test.java.businesslogic_mocktester.promotionblmocktester;

import main.java.businesslogic.promotionbl.PromotionTool;
import main.java.businesslogic_mock.promotionblmock.PromotionToolMock;
import org.junit.Test;
import main.java.vo.promotion.PromotionQueryVO;

import static org.junit.Assert.*;

public class PromotionToolMockTest {
    @Test
    public void getPromotionList() throws Exception {
        PromotionTool tool = new PromotionToolMock();
        assertEquals(tool.getPromotionList(new PromotionQueryVO()).size(), 1);
    }

}