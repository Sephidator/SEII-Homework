package test.java.server_bldriver.goodsdataservicedriver;

import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.po.goods.GoodsPO;
import main.java.server_dataservicestub.goodsdataservicestub.GoodsDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsDataServiceDriver {
    GoodsDataService service = new GoodsDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(true, service.find("").isVisible());
    }

    @Test
    public void finds() throws Exception {
        assertEquals(1, service.finds(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("00000001", service.insert(new GoodsPO()));
    }

}