package test.java.client_presentationdriver.promotionblservicedriver;

import main.java.businesslogicservice.promotionblservice.PromotionBlService;
import main.java.client_blservicestub.promotionblservicestub.PromotionBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class PromotionBlServiceDriver {
    PromotionBlService service=new PromotionBlServiceStub();

    @Test
    public void getPromotionList() throws Exception {
        assertEquals(0,service.getPromotionList(null).size());
    }

    @Test
    public void addPromotion() throws Exception {
        assertEquals("",service.addPromotion(null));
    }

    @Test
    public void editPromotion() throws Exception {
    }

    @Test
    public void deletePromotion() throws Exception {
    }

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(0,service.getGoodsList(null).size());
    }

}