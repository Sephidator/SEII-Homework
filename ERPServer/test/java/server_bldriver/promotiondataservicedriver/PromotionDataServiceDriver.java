package server_bldriver.promotiondataservicedriver;

import data.datautility.ResultMessage;
import dataservice.promotiondataservice.PromotionDataService;
import org.junit.Test;
import po.PromotionPO;
import po.PromotionTotalPO;
import server_dataservicestub.promotiondataservicestub.PromotionDataServiceStub;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PromotionDataServiceDriver {
    PromotionDataService promotionDataService = new PromotionDataServiceStub();
    ArrayList<PromotionPO> pos = new ArrayList<>();
    PromotionPO po = new PromotionTotalPO("001", 2, null, null, 1000, 100, null);

    @Test
    public void finds() throws Exception {
        pos.clear();
        pos.add(po);
        assertEquals(promotionDataService.finds().size(), pos.size());
    }

    @Test
    public void find() throws Exception {
        assertEquals(promotionDataService.find("001").getID(), "001");
    }

    @Test
    public void insert() throws Exception {
        assertEquals(promotionDataService.insert(new PromotionPO()), ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

}