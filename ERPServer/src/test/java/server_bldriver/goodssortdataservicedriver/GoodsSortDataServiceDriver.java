package test.java.server_bldriver.goodssortdataservicedriver;

import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsSortPO;
import main.java.server_dataservicestub.goodssortdataservicestub.GoodsSortDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsSortDataServiceDriver {
    GoodsSortDataService service = new GoodsSortDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1, service.find(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("00000001", service.insert(new GoodsSortPO()));
    }

}