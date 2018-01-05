package test.java.businesslogic_mocktester.financeblmocktester;

import main.java.businesslogic.financebl.PaymentBillBl;
import main.java.businesslogic.financebl.PaymentBillTool;
import main.java.businesslogic_mock.financeblmock.PaymentBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentBillToolMockTester {
    PaymentBillTool tool=new PaymentBillToolMock();

    @Test
    public void pass() throws Exception {
    }

    @Test
    public void reject() throws Exception {
    }

    @Test
    public void getPaymentBillList() throws Exception {
        assertEquals(0,tool.getPaymentBillList(null).size());
    }

    @Test
    public void submit() throws Exception {
        assertEquals("",tool.submit(null));
    }

}