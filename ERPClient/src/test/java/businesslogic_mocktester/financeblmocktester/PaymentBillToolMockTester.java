package businesslogic_mocktester.financeblmocktester;

import businesslogic.blutility.ResultMessage;
import businesslogic.financebl.PaymentBillTool;
import businesslogic_mock.financeblmock.PaymentBillToolMock;
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