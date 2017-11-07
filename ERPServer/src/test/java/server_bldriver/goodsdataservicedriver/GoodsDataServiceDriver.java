package server_bldriver.goodsdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.goodsdataservice.GoodsDataService;
import po.goods.GoodsPO;
import po.goods.GoodsQueryPO;
import server_dataservicestub.goodsdataservicestub.GoodsDataServiceStub;

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