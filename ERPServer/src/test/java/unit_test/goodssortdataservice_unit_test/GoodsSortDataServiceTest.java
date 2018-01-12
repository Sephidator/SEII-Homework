package test.java.unit_test.goodssortdataservice_unit_test;

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
        GoodsSortPO goodsSortPO = service.getRoot();
        while (goodsSortPO.getChildrenID().size() > 0) {
            ArrayList<String> list = goodsSortPO.getChildrenID();
            goodsSortPO = service.find(list.get(list.size() - 1));
        }

        assertEquals("GoodsSort", service.insert(new GoodsSortPO(goodsSortPO.getName() + "0", goodsSortPO.getID(), new ArrayList<>(), new ArrayList<>(), "")).substring(0, 9));
    }

}