package test.java.server_bldriver.purchasedataservicedriver;

import main.java.dataservice.purchasedataservice.PurchaseTradeBillDataService;
import main.java.po.bill.purchasebill.PurchaseTradeBillPO;
import main.java.server_dataservicestub.purchasedataservicestub.PurchaseTradeBillDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseTradeBillDataServiceDriver {
    PurchaseTradeBillDataService service=new PurchaseTradeBillDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1,service.find(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("JHD-20171212-12345",service.insert(new PurchaseTradeBillPO()));
    }

}