package server_bldriver.goodssortdataservicedriver;

import dataservice.goodssortdataservice.GoodsSortDataService;
import org.junit.Test;
import data.datautility.ResultMessage;
import po.GoodsSortPO;
import server_dataservicestub.goodssortdataservicestub.GoodsSortDataServiceStub;

import static org.junit.Assert.*;

public class GoodsSortDataServiceDriver {

    GoodsSortPO goodsSortPO=new GoodsSortPO("000000001","分类A","000000001",null,null,"无");

    GoodsSortDataService goodsSortDataService=new GoodsSortDataServiceStub();

    @Test
    public void findGoodsSort() throws Exception {
        assertEquals("000000001",goodsSortDataService.findGoodsSort("000000001").getID());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,goodsSortDataService.insert(goodsSortPO));
    }

    @Test
    public void delete() throws Exception {
        assertEquals(ResultMessage.success,goodsSortDataService.delete(goodsSortPO));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,goodsSortDataService.update(goodsSortPO));
    }

    @Test
    public void detail() throws Exception {
        assertEquals("000000001",goodsSortDataService.detail(goodsSortPO).getID());
    }

}