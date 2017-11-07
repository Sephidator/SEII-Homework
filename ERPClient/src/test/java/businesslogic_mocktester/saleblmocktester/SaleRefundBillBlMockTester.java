package businesslogic_mocktester.saleblmocktester;

import businesslogic.blutility.ResultMessage;
import businesslogic.salebl.SaleRefundBillTool;
import businesslogic_mock.saleblmock.SaleRefundBillToolMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SaleRefundBillBlMockTester {
    SaleRefundBillTool tool=new SaleRefundBillToolMock();
    
    @Test
    public void pass() throws Exception {
        assertEquals(ResultMessage.success,tool.pass(null));
    }

    @Test
    public void reject() throws Exception {
        assertEquals(ResultMessage.success,tool.reject(null));
    }

    @Test
    public void getSaleRefundBillList() throws Exception {
        assertEquals(1,tool.getSaleRefundBillList(null).size());
    }

}