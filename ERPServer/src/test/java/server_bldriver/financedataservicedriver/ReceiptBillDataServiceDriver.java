package test.java.server_bldriver.financedataservicedriver;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.financedataservice.ReceiptBillDataService;
import org.junit.Test;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.financebill.ReceiptBillPO;
import main.java.server_dataservicestub.financedataservicestub.ReceiptBillDataServiceStub;

import static org.junit.Assert.*;

public class ReceiptBillDataServiceDriver {
    ReceiptBillDataService receiptBillDataService = new ReceiptBillDataServiceStub();
    @Test
    public void find() throws Exception {
        assertEquals("SKD-20171106-00001",receiptBillDataService.find(new BillQueryPO()).get(0).getID());
    }

    @Test
    public void getID() throws Exception {
        assertEquals("SKD-20171106-00001",receiptBillDataService.getID());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,receiptBillDataService.insert(new ReceiptBillPO()));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,receiptBillDataService.update(new ReceiptBillPO()));
    }

}