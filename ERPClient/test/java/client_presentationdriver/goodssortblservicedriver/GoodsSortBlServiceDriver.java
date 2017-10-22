package client_presentationdriver.goodssortblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.goodssortblservice.GoodsSortBlService;
import client_blservicestub.goodssortblservicestub.GoodsSortBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsSortBlServiceDriver {

    GoodsSortBlService goodsSortBlService=new GoodsSortBlServiceStub();

    @Test
    public void insertGoodsSort() throws Exception {

        assertEquals(ResultMessage.success,goodsSortBlService.insertGoodsSort("000000002","分类B","000000001",
                "无"));
    }

    @Test
    public void deleteGoodsSort() throws Exception {

        assertEquals(ResultMessage.success,goodsSortBlService.deleteGoodsSort("000000001"));
    }

    @Test
    public void updateGoodsSort() throws Exception {

        assertEquals(ResultMessage.success,goodsSortBlService.updateGoodsSort("000000002","分类B","000000001",
                "无"));
    }

    @Test
    public void detailGoodsSort() throws Exception {

        assertEquals(ResultMessage.success,goodsSortBlService.detailGoodsSort("000000002"));
    }

    @Test
    public void getID() throws Exception {
        assertEquals("000000002",goodsSortBlService.getID("000000001"));
    }

}