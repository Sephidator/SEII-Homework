package test.java.unit_test.financebltest;

import main.java.businesslogicfactory.financeblfactory.PaymentBillBlFactory;
import main.java.businesslogicservice.financeblservice.PaymentBillBlService;
import main.java.client_blservicestub.financeblservicestub.PaymentBillBlServiceStub;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaymentBillBlServiceTest {
    PaymentBillBlService service= PaymentBillBlFactory.getService();

    @Test
    public void getPaymentBillList() throws Exception {
        assertEquals(true,service.getPaymentBillList(
                new BillQueryVO("审批通过",null,null,"付款单",null,null)).size()>=0);
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