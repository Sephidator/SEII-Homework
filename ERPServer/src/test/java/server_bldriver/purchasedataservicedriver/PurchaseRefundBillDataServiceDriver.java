package test.java.server_bldriver.purchasedataservicedriver;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.purchasedataservice.PurchaseRefundBillDataService;
import org.junit.Test;
import main.java.server_dataservicestub.purchasedataservicestub.PurchaseRefundBillDataServiceStub;

import static org.junit.Assert.*;

public class PurchaseRefundBillDataServiceDriver {
    PurchaseRefundBillDataService service=new PurchaseRefundBillDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1,service.find(null).size());
    }

    @Test
    public void getID() throws Exception {
        assertEquals("123",service.getID());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,service.insert(null));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,service.update(null));
    }

}