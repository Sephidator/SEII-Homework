package test.java.server_bldriver.goodsdataservicedriver;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;
import main.java.server_dataservicestub.goodsdataservicestub.GoodsDataServiceStub;

import static org.junit.Assert.*;

public class GoodsDataServiceDriver {

    GoodsDataService goodsDataService=new GoodsDataServiceStub();
    GoodsPO goodsPO=new GoodsPO();
    GoodsQueryPO goodsQueryPO=new GoodsQueryPO();

    @org.junit.Test
    public void find() throws Exception {
        assertEquals("1",goodsDataService.find(goodsQueryPO).getComment());
    }

    @org.junit.Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,goodsDataService.insert(goodsPO));
    }

    @org.junit.Test
    public void delete() throws Exception {
        assertEquals(ResultMessage.success,goodsDataService.delete(goodsPO));
    }

    @org.junit.Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,goodsDataService.update(goodsPO));
    }

    @org.junit.Test
    public void getGoodsID() throws Exception {
        assertEquals(ResultMessage.success,goodsDataService.getGoodsID());
    }

}