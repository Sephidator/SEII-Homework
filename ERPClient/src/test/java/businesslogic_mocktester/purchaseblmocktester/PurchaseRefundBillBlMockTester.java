package businesslogic_mocktester.purchaseblmocktester;

import businesslogic.blutility.ResultMessage;
import businesslogic.purchasebl.PurchaseRefundBillTool;
import businesslogic_mock.purchaseblmock.PurchaseRefundBillToolMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PurchaseRefundBillBlMockTester {
    PurchaseRefundBillTool tool=new PurchaseRefundBillToolMock();

    @Test
    public void pass() throws Exception {
        assertEquals(ResultMessage.success,tool.pass(null));
    }

    @Test
    public void reject() throws Exception {
        assertEquals(ResultMessage.success,tool.reject(null));
    }

    @Test
    public void getPurchaseRefundBillList() throws Exception {
        assertEquals(1,tool.getPurchaseRefundBillList(null).size());
    }
}