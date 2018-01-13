package test.java.unit_test.goodssortdatatest;

import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsSortPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GoodsSortDataServiceTest {
    GoodsSortDataService service;

    public GoodsSortDataServiceTest() throws Exception {
        service = (GoodsSortDataService) Naming.lookup("rmi://127.0.0.1:7200/GoodsSortDataService");
    }

    @Test
    public void find() throws Exception {
        assertEquals("GoodsSort00000001", service.find("GoodsSort00000001").getID());
    }

    @Test
    public void getRoot() throws Exception {
        assertEquals("GoodsSort00000001", service.getRoot().getID());
    }

    @Test
    public void insert() throws Exception {
        try {
            assertEquals("GoodsSort", service.insert(new GoodsSortPO("灯泡", service.getRoot().getID(), new ArrayList<>(), new ArrayList<>(), "")).substring(0, 9));
        } catch (Exception e) {

        }
    }

}