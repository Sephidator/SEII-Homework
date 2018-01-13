package test.java.unit_test.goodssortbltest;


import main.java.businesslogic.goodssortbl.GoodsSortBl;
import main.java.businesslogicfactory.goodssortblfactory.GoodsSortBlFactory;
import main.java.businesslogicservice.goodssortblservice.GoodsSortBlService;
import main.java.client_blservicestub.goodssortblservicestub.GoodsSortBlServiceStub;
import main.java.exception.NotExistException;
import main.java.vo.goods.GoodsSortVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoodsSortBlServiceTest {
    GoodsSortBlService service= GoodsSortBlFactory.getService();

    @Test
    public void getRoot() throws Exception {
        assertEquals("GoodsSort",service.getRoot().getID().substring(0,9));
    }

    @Test
    public void find() throws Exception {
        assertEquals("GoodsSort",service.find("GoodsSort00000001").getID().substring(0,9));
    }

    @Test
    public void addGoodsSort() throws Exception {
        try{
            assertEquals("GoodsSort",service.addGoodsSort(new GoodsSortVO()).substring(0,9));
        }catch(NotExistException e){}
    }

}