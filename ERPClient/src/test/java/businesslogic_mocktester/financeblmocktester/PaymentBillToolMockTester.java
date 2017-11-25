package test.java.businesslogic_mocktester.financeblmocktester;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.financebl.PaymentBillTool;
import main.java.businesslogic_mock.financeblmock.PaymentBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentBillToolMockTester {
    PaymentBillTool paymentBillTool = new PaymentBillToolMock();
    @Test
    public void pass() throws Exception {
        assertEquals(ResultMessage.success,paymentBillTool.pass(null));
    }

    @Test
    public void reject() throws Exception {
        assertEquals(ResultMessage.success,paymentBillTool.reject(null));
    }

    @Test
    public void getPaymentBillList() throws Exception {
        assertEquals(1,paymentBillTool.getPaymentBillList(null).size());
    }

}