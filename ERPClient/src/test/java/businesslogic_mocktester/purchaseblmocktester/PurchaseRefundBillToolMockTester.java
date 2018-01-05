package test.java.businesslogic_mocktester.purchaseblmocktester;

import main.java.businesslogic.purchasebl.PurchaseRefundBillTool;
import main.java.businesslogic_mock.purchaseblmock.PurchaseRefundBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseRefundBillToolMockTester {
    PurchaseRefundBillTool tool=new PurchaseRefundBillToolMock();

    @Test
    public void pass() throws Exception {
    }

    @Test
    public void reject() throws Exception {
    }

    @Test
    public void getPurchaseRefundBillList() throws Exception {
        assertEquals(0,tool.getPurchaseRefundBillList(null).size());
    }

}