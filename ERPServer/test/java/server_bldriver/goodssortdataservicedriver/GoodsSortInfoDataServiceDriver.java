package server_bldriver.goodssortdataservicedriver;

import dataservice.goodssortdataservice.GoodsSortInfoDataService;
import org.junit.Test;
import po.GoodsSortPO;
import server_dataservicestub.goodssortdataservicestub.GoodsSortInfoDataServiceStub;

import static org.junit.Assert.*;

public class GoodsSortInfoDataServiceDriver {

    GoodsSortPO goodsSortPO=new GoodsSortPO("000000001","分类A","000000001",null,null,"无");

    GoodsSortInfoDataService goodsSortInfoDataService=new GoodsSortInfoDataServiceStub();

    @Test
    public void show() throws Exception {
        assertEquals("000000001",goodsSortInfoDataService.show(goodsSortPO).getID());
    }

}