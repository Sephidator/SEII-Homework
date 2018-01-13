package test.java.unit_test.reportbltest;

import main.java.businesslogicfactory.reportblfactory.BusinessHistoryBlFactory;
import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;
import main.java.vo.bill.financebill.CashBillVO;
import main.java.vo.bill.financebill.PaymentBillVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.user.UserVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BusinessHistoryBlServiceTest {
    BusinessHistoryBlService service= BusinessHistoryBlFactory.getService();

    @Test
    public void getBillList() throws Exception {
        assertEquals(true,service.getBillList(null).size()>=0);
    }

    @Test
    public void reversePaymentBill() throws Exception {
        assertEquals("FKD",service.reversePaymentBill(new PaymentBillVO(),new UserVO()).substring(0,3));
    }

    @Test
    public void reverseReceiptBill() throws Exception {
        assertEquals("SKD",service.reverseReceiptBill(new ReceiptBillVO(),new UserVO()).substring(0,3));

    }

    @Test
    public void reverseCashBill() throws Exception {
        assertEquals("XJFYD",service.reverseCashBill(new CashBillVO(),new UserVO()).substring(0,5));
    }

}