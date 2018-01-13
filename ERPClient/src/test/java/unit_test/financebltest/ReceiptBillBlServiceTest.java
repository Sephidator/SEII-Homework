package test.java.unit_test.financebltest;

import main.java.businesslogicfactory.financeblfactory.ReceiptBillBlFactory;
import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;
import main.java.client_blservicestub.financeblservicestub.ReceiptBillBlServiceStub;
import main.java.vo.bill.financebill.ReceiptBillVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReceiptBillBlServiceTest {
    ReceiptBillBlService service= ReceiptBillBlFactory.getService();

    @Test
    public void getReceiptBillList() throws Exception {
        assertEquals(true,service.getReceiptBillList(null).size()>=0);
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
        assertEquals("SKD",service.submit(new ReceiptBillVO()).substring(0,3));
    }

    @Test
    public void editReceiptBill() throws Exception {
    }

}