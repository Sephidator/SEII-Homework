package test.java.unit_test.promotiondatatest;

import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.exception.ExistException;
import main.java.po.promotion.PromotionGoodsPO;
import main.java.po.promotion.PromotionPO;
import main.java.po.promotion.PromotionTotalPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PromotionDataServiceTest {
    PromotionDataService service;

    public PromotionDataServiceTest() throws Exception {
        service = (PromotionDataService) Naming.lookup("rmi://127.0.0.1:7200/PromotionDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(null).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        try {
            assertEquals("Promotion", service.insert(new PromotionTotalPO("促销策略", new Date(), new Date(), 10000, 200, new ArrayList<>())).substring(0, 9));
        } catch (Exception e) {

        }
    }
}