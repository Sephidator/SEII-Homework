package test.java.client_presentationdriver.promotionblservicedriver;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.promotionblservice.PromotionBlService;
import main.java.client_blservicestub.promotionblservicestub.PromotionBlServiceStub;
import org.junit.Test;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import static org.junit.Assert.*;

public class PromotionBlServiceDriver {
    PromotionBlService service = new PromotionBlServiceStub();

    @Test
    public void getPromotionList() throws Exception {
        assertEquals(service.getPromotionList(new PromotionQueryVO()).size(), 3);
    }

    @Test
    public void addPromotion() throws Exception {
        assertEquals(service.addPromotion(new PromotionVO()), ResultMessage.success);
    }

    @Test
    public void editPromotion() throws Exception {
        assertEquals(service.editPromotion(new PromotionVO()), ResultMessage.success);
    }

    @Test
    public void deletePromotion() throws Exception {
        assertEquals(service.deletePromotion(new PromotionVO()), ResultMessage.success);
    }

    @Test
    public void getID() throws Exception {
        assertEquals(service.getID(), "12345");
    }

}