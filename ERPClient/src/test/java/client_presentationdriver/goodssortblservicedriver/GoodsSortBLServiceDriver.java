package test.java.client_presentationdriver.goodssortblservicedriver;


import main.java.businesslogicservice.goodssortblservice.GoodsSortBlService;
import main.java.client_blservicestub.goodssortblservicestub.GoodsSortBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsSortBlServiceDriver {
    GoodsSortBlService service=new GoodsSortBlServiceStub();

    @Test
    public void getRoot() throws Exception {
        assertEquals("",service.getRoot().getID());
    }

    @Test
    public void find() throws Exception {
        assertEquals("",service.find(null).getID());
    }

    @Test
    public void addGoodsSort() throws Exception {
        assertEquals("",service.addGoodsSort(null));
    }

    @Test
    public void deleteGoodsSort() throws Exception {
    }

    @Test
    public void editGoodsSort() throws Exception {
    }

}