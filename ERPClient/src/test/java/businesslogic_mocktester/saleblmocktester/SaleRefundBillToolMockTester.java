package test.java.businesslogic_mocktester.saleblmocktester;

import main.java.businesslogic.salebl.SaleRefundBillTool;
import main.java.businesslogic_mock.saleblmock.SaleRefundBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleRefundBillToolMockTester {
    SaleRefundBillTool tool=new SaleRefundBillToolMock();

    @Test
    public void pass() throws Exception {
    }

    @Test
    public void reject() throws Exception {
    }

    @Test
    public void getSaleRefundBillList() throws Exception {
        assertEquals(0,tool.getSaleRefundBillList(null).size());
    }

}