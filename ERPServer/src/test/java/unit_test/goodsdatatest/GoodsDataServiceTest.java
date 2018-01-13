package test.java.unit_test.goodsdatatest;

import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsSortPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

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
        GoodsSortDataService goodsSortDataService = (GoodsSortDataService) Naming.lookup("rmi://127.0.0.1:7200/GoodsSortDataService");
        GoodsSortPO goodsSortPO = goodsSortDataService.getRoot();
        while (goodsSortPO.getChildrenID().size() > 0)
            goodsSortPO = goodsSortDataService.find(goodsSortPO.getChildrenID().get(0));

        ArrayList<GoodsPO> list = service.finds(null);
        GoodsPO goodsPO = list.get(list.size() - 1);
        goodsPO.setName(goodsPO.getName() + "0");
        goodsPO.setModel(goodsPO.getModel() + "1");
        assertEquals("Goods", service.insert(goodsPO).substring(0, 5));
    }
}