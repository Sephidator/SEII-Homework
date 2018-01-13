package test.java.unit_test.promotionbltest;

import main.java.businesslogicfactory.promotionblfactory.PromotionBlFactory;
import main.java.businesslogicservice.promotionblservice.PromotionBlService;
import main.java.client_blservicestub.promotionblservicestub.PromotionBlServiceStub;
import main.java.exception.ExistException;
import main.java.vo.promotion.PromotionVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PromotionBlServiceTest {
    PromotionBlService service= PromotionBlFactory.getService();

    @Test
    public void getPromotionList() throws Exception {
        assertEquals(true,service.getPromotionList(null).size()>=0);
    }

    @Test(expected = ExistException.class)
    public void addPromotion() throws Exception {
        assertEquals("Promotion", service.addPromotion(new PromotionVO()).substring(0, 9));
    }

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(true,service.getGoodsList(null).size()>=0);
    }

}