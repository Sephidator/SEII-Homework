package test.java.businesslogic_mocktester.financeblmocktester;

import main.java.businesslogic.financebl.CashBillBl;
import main.java.businesslogic.financebl.CashBillTool;
import main.java.businesslogic_mock.financeblmock.CashBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashBillToolMockTester {
    CashBillTool tool=new CashBillToolMock();

    @Test
    public void pass() throws Exception {
    }

    @Test
    public void reject() throws Exception {
    }

    @Test
    public void getCashBillList() throws Exception {
        assertEquals(0,tool.getCashBillList(null).size());
    }

    @Test
    public void submit() throws Exception {
        assertEquals("",tool.submit(null));
    }

}