package test.java.server_bldriver.purchasedataservicedriver;

import main.java.dataservice.purchasedataservice.PurchaseRefundBillDataService;
import main.java.po.bill.purchasebill.PurchaseRefundBillPO;
import main.java.server_dataservicestub.purchasedataservicestub.PurchaseRefundBillDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseRefundBillDataServiceDriver {
    PurchaseRefundBillDataService service = new PurchaseRefundBillDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1, service.find(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("JHTHD-20171212-12345", service.insert(new PurchaseRefundBillPO()));
    }

}