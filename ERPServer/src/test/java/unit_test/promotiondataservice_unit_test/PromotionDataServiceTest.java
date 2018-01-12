package test.java.unit_test.promotiondataservice_unit_test;

import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.po.promotion.PromotionPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

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
        ArrayList<PromotionPO> list = service.finds(null);
        PromotionPO promotionPO = list.get(list.size() - 1);
        promotionPO.setName(promotionPO.getName() + "0");

        assertEquals("Promotion", service.insert(promotionPO).substring(0, 9));
    }
}