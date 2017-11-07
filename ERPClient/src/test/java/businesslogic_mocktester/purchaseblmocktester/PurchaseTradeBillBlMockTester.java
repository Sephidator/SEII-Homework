package businesslogic_mocktester.purchaseblmocktester;

import businesslogic.blutility.ResultMessage;
import businesslogic.purchasebl.PurchaseTradeBillTool;
import businesslogic_mock.purchaseblmock.PurchaseTradeBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseTradeBillBlMockTester {
    PurchaseTradeBillTool tool=new PurchaseTradeBillToolMock();

    @Test
    public void pass() throws Exception {
        assertEquals(ResultMessage.success,tool.pass(null));
    }

    @Test
    public void reject() throws Exception {
        assertEquals(ResultMessage.success,tool.reject(null));
    }

    @Test
    public void getPurchaseTradeBillList() throws Exception {
        assertEquals(1,tool.getPurchaseTradeBillList(null).size());
    }
}