package server_bldriver.promotiondataservicedriver;

import data.datautility.ResultMessage;
import dataservice.promotiondataservice.PromotionDataService;
import org.junit.Test;
import po.promotion.PromotionPO;
import po.promotion.PromotionQueryPO;
import server_bldriver.promotiondataservicestub.PromotionDataServiceStub;

import static org.junit.Assert.*;

public class PromotionDataServiceDriver {
    PromotionDataService service = new PromotionDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(service.find(new PromotionQueryPO()).size(), 1);
    }

    @Test
    public void insert() throws Exception {
        assertEquals(service.insert(new PromotionPO()), ResultMessage.success);
    }

    @Test
    public void delete() throws Exception {
        assertEquals(service.delete(new PromotionPO()), ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        assertEquals(service.update(new PromotionPO()), ResultMessage.success);
    }

    @Test
    public void getPromotionID() throws Exception {
        assertEquals(service.getPromotionID(), "12345");
    }

}