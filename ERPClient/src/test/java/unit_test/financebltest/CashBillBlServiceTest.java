package test.java.unit_test.financebltest;

import main.java.businesslogicfactory.financeblfactory.CashBillBlFactory;
import main.java.businesslogicservice.financeblservice.CashBillBlService;
import main.java.client_blservicestub.financeblservicestub.CashBillBlServiceStub;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.financebill.CashBillVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CashBillBlServiceTest {
    CashBillBlService service= CashBillBlFactory.getService();

    @Test
    public void getCashBillList() throws Exception {
        assertEquals(true,service.getCashBillList(
                new BillQueryVO("审批通过",null,null,"现金费用单",null,null)).size()>=0);
    }

    @Test
    public void getAccountList() throws Exception {
        assertEquals(true,service.getAccountList(null).size()>=0);
    }

    @Test
    public void submit() throws Exception {
        assertEquals("XJFYD",service.submit(new CashBillVO()).substring(0,5));
    }

}