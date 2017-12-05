package test.java.server_bldriver.promotiondataservicedriver;

import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.po.promotion.PromotionPO;
import main.java.server_dataservicestub.promotiondataservicestub.PromotionDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class PromotionDataServiceDriver {
    PromotionDataService service=new PromotionDataServiceStub();

    @Test
    public void finds() throws Exception {
        assertEquals(3,service.finds(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("00000001",service.insert(new PromotionPO()));
    }
}