package test.java.client_presentationdriver.reportblservicedriver;

import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;
import main.java.client_blservicestub.reportblservicestub.BusinessHistoryBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class BusinessHistoryBlServiceDriver {
    BusinessHistoryBlService service=new BusinessHistoryBlServiceStub();

    @Test
    public void getBillList() throws Exception {
        assertEquals(0,service.getBillList(null).size());
    }

    @Test
    public void reversePaymentBill() throws Exception {
        assertEquals("",service.reversePaymentBill(null,null));
    }

    @Test
    public void reverseReceiptBill() throws Exception {
        assertEquals("",service.reverseReceiptBill(null,null));
    }

    @Test
    public void reverseCashBill() throws Exception {
        assertEquals("",service.reverseCashBill(null,null));
    }

}