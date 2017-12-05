package test.java.server_bldriver.saledataservicedriver;

import main.java.dataservice.saledataservice.SaleRefundBillDataService;
import main.java.po.bill.salebill.SaleRefundBillPO;
import main.java.server_dataservicestub.saledataservicestub.SaleRefundBillDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleRefundBillDataServiceDriver {
    SaleRefundBillDataService service = new SaleRefundBillDataServiceStub();

    @Test
    public void finds() throws Exception {
        assertEquals(1, service.finds(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("XSTHD-20171212-12345", service.insert(new SaleRefundBillPO()));
    }

}