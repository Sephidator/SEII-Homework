package server_bldriver.goodssortdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.goodssortdataservice.GoodsSortDataService;
import org.junit.Test;
import po.goods.GoodsSortPO;
import po.goods.GoodsSortQueryPO;
import server_dataservicestub.goodssortdataservicestub.GoodsSortDataServiceStub;

import static org.junit.Assert.*;

public class GoodsSortDataServiceDriver {

    GoodsSortDataService goodsSortDataService=new GoodsSortDataServiceStub();
    GoodsSortPO goodsSortPO=new GoodsSortPO();
    GoodsSortQueryPO goodsSortQueryPO=new GoodsSortQueryPO();
    @Test
    public void find() throws Exception {
        assertEquals("1",goodsSortDataService.find(goodsSortQueryPO).getComment());
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
    public void getGoodsSortID() throws Exception {
        assertEquals(ResultMessage.success,goodsSortDataService.getGoodsSortID());
    }

}