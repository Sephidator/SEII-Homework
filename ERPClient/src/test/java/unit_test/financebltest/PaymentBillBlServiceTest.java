package test.java.unit_test.financebltest;

import main.java.businesslogicservice.financeblservice.PaymentBillBlService;
import main.java.client_blservicestub.financeblservicestub.PaymentBillBlServiceStub;
import main.java.vo.bill.financebill.PaymentBillVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaymentBillBlServiceTest {
    PaymentBillBlService service=new PaymentBillBlServiceStub();

    @Test
    public void getPaymentBillList() throws Exception {
        assertEquals(true,service.getPaymentBillList(null).size()>=0);
    }

    @Test
    public void getClientList() throws Exception {
        assertEquals(true,service.getClientList(null).size()>=0);
    }

    @Test
    public void getAccountList() throws Exception {
        assertEquals(true,service.getAccountList(null).size()>=0);
    }

    @Test
    public void submit() throws Exception {
        assertEquals("FKD",service.submit(new PaymentBillVO()).substring(0,3));
    }

    @Test
    public void editPaymentBill() throws Exception {
    }

}