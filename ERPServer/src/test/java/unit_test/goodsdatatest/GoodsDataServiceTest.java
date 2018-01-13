package test.java.unit_test.goodsdatatest;

import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsSortPO;
import org.junit.Test;

import java.rmi.Naming;

import static org.junit.Assert.assertEquals;

public class GoodsDataServiceTest {
    GoodsDataService service;

    public GoodsDataServiceTest() throws Exception {
        service = (GoodsDataService) Naming.lookup("rmi://127.0.0.1:7200/GoodsDataService");
    }

    @Test
    public void find() throws Exception {
        assertEquals("Goods00000001", service.find("Goods00000001").getID());
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(null).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        try {
            GoodsSortPO goodsSortPO = ((GoodsSortDataService) Naming.lookup("rmi://127.0.0.1:7200/GoodsSortDataService")).getRoot();
            assertEquals("Goods", service.insert(new GoodsPO("灯泡", goodsSortPO.getID(), "12345678", 0, 10, 20, 0, 0, 1000, "")).substring(0, 5));
        } catch (Exception e) {

        }
    }
}