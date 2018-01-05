package test.java.client_presentationdriver.financeblservicedriver;

import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;
import main.java.client_blservicestub.financeblservicestub.ReceiptBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReceiptBillBlServiceDriver {
    ReceiptBillBlService service=new ReceiptBillBlServiceStub();

    @Test
    public void getReceiptBillList() throws Exception {
        assertEquals(0,service.getReceiptBillList(null).size());
    }

    @Test
    public void getClientList() throws Exception {
        assertEquals(0,service.getClientList(null).size());
    }

    @Test
    public void getAccountList() throws Exception {
        assertEquals(0,service.getAccountList(null).size());
    }

    @Test
    public void submit() throws Exception {
        assertEquals("",service.submit(null));
    }

    @Test
    public void editReceiptBill() throws Exception {
    }

}