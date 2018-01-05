package test.java.businesslogic_mocktester.purchaseblmocktester;

import main.java.businesslogic.purchasebl.PurchaseTradeBillTool;
import main.java.businesslogic_mock.purchaseblmock.PurchaseTradeBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseTradeBillToolMockTester {
    PurchaseTradeBillTool tool=new PurchaseTradeBillToolMock();

    @Test
    public void pass() throws Exception {
    }

    @Test
    public void reject() throws Exception {
    }

    @Test
    public void getPurchaseTradeBillList() throws Exception {
        assertEquals(0,tool.getPurchaseTradeBillList(null).size());
    }

}