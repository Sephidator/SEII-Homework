package server_bldriver.goodsdataservicedriver;

import data.datautility.ResultMessage;
import dataservice.goodsdataservice.GoodsDataService;
import po.GoodsPO;
import server_dataservicestub.goodsdataservicestub.GoodsDataServiceStub;

import static org.junit.Assert.*;

public class GoodsDataServiceDriver {

    GoodsDataService goodsDataService=new GoodsDataServiceStub();
    GoodsPO goodsPO=new GoodsPO("000000001","商品A","000000001","型号A",100,10.0,14.0,
            11.0,15.0,10,"无");

    @org.junit.Test
    public void findGoods() throws Exception {
        assertEquals("000000001",goodsDataService.findGoods("000000001","商品A").getID());

    }

    @org.junit.Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,goodsDataService.insert(null));
    }

    @org.junit.Test
    public void delete() throws Exception {
        assertEquals(ResultMessage.success,goodsDataService.delete(null));
    }

    @org.junit.Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,goodsDataService.update(null));
    }

    @org.junit.Test
    public void detail() throws Exception {
        assertEquals("000000001",goodsDataService.detail(goodsPO).getID());
    }

}