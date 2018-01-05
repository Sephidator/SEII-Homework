package test.java.client_presentationdriver.financeblservicedriver;

import main.java.businesslogicservice.financeblservice.CashBillBlService;
import main.java.client_blservicestub.financeblservicestub.CashBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashBillBlServiceDriver {
    CashBillBlService service=new CashBillBlServiceStub();

    @Test
    public void getCashBillList() throws Exception {
        assertEquals(0,service.getCashBillList(null).size());
    }

    @Test
    public void getAccountList() throws Exception {
        assertEquals(0,service.getCashBillList(null).size());
    }

    @Test
    public void submit() throws Exception {
        assertEquals("",service.submit(null));
    }

    @Test
    public void editCashBill() throws Exception {
    }

}