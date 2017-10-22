package server_bldriver.goodsdataservicedriver;

import dataservice.goodsdataservice.GoodsInfoDataService;
import org.junit.Test;
import po.GoodsPO;
import server_dataservicestub.goodsdataservicestub.GoodsInfoDataServiceStub;

import static org.junit.Assert.*;

public class GoodsInfoDataServiceDriver {

    GoodsPO goodsPO=new GoodsPO("000000001","商品A","000000001","型号A",100,10.0,14.0,
            11.0,15.0,10,"无");

    GoodsInfoDataService goodsInfoDataService=new GoodsInfoDataServiceStub();

    @Test
    public void show() throws Exception {
        goodsInfoDataService.show(goodsPO);
        assertEquals("000000001",goodsInfoDataService.show(goodsPO).getID());
    }

}