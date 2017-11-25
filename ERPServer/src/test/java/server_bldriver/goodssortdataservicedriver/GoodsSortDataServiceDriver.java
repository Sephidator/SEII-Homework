package test.java.server_bldriver.goodssortdataservicedriver;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import org.junit.Test;
import main.java.po.goods.GoodsSortPO;
import main.java.po.goods.GoodsSortQueryPO;
import main.java.server_dataservicestub.goodssortdataservicestub.GoodsSortDataServiceStub;

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