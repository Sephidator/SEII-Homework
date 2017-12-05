package test.java.server_bldriver.financedataservicedriver;

import main.java.dataservice.financedataservice.PaymentBillDataService;
import main.java.po.bill.financebill.PaymentBillPO;
import main.java.server_dataservicestub.financedataservicestub.PaymentBillDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentBillDataServiceDriver {
    PaymentBillDataService service=new PaymentBillDataServiceStub();

    @Test
    public void finds() throws Exception {
        assertEquals(1,service.finds(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("FKD-20171212-12345",service.insert(new PaymentBillPO()));
    }

}