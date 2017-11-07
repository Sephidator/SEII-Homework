package businesslogic_mocktester.promotionblmocktester;

import businesslogic.promotionbl.PromotionTool;
import businesslogic_mock.promotionblmock.PromotionToolMock;
import org.junit.Test;
import vo.promotion.PromotionQueryVO;

import static org.junit.Assert.*;

public class PromotionToolMockTest {
    @Test
    public void getPromotionList() throws Exception {
        PromotionTool tool = new PromotionToolMock();
        assertEquals(tool.getPromotionList(new PromotionQueryVO()).size(), 1);
    }

}