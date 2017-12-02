package businesslogic_mocktester.financeblmocktester;

import businesslogic.blutility.ResultMessage;
import businesslogic.financebl.CashBillTool;
import businesslogic_mock.financeblmock.CashBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashBillToolMockTester {
    CashBillTool cashBillTool = new CashBillToolMock();
    @Test
    public void pass() throws Exception {
        assertEquals(ResultMessage.success,cashBillTool.pass(null));
    }

    @Test
    public void reject() throws Exception {
        assertEquals(ResultMessage.success,cashBillTool.reject(null));
    }

    @Test
    public void getCashBillList() throws Exception {
        assertEquals(1,cashBillTool.getCashBillList(null).size());
    }

}