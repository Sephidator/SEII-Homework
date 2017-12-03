package test.java.server_bldriver.financedataservicedriver;

import main.java.dataservice.financedataservice.ReceiptBillDataService;
import main.java.po.bill.financebill.ReceiptBillPO;
import main.java.server_dataservicestub.financedataservicestub.ReceiptBillDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReceiptBillDataServiceDriver {
    ReceiptBillDataService service=new ReceiptBillDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1,service.find(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("SKD-20171212-12345",service.insert(new ReceiptBillPO()));
    }

}