package test.java.client_presentationdriver.financeblservicedriver;

import main.java.businesslogicservice.financeblservice.PaymentBillBlService;
import main.java.client_blservicestub.financeblservicestub.PaymentBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentBillBlServiceDriver {
    PaymentBillBlService service=new PaymentBillBlServiceStub();

    @Test
    public void getPaymentBillList() throws Exception {
        assertEquals(0,service.getPaymentBillList(null).size());
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
    public void editPaymentBill() throws Exception {
    }

}