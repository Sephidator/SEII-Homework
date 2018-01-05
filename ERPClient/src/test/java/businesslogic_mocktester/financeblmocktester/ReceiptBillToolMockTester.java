package test.java.businesslogic_mocktester.financeblmocktester;

import main.java.businesslogic.financebl.ReceiptBillBl;
import main.java.businesslogic.financebl.ReceiptBillTool;
import main.java.businesslogic_mock.financeblmock.ReceiptBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReceiptBillToolMockTester {
    ReceiptBillTool tool=new ReceiptBillToolMock();

    @Test
    public void pass() throws Exception {
    }

    @Test
    public void reject() throws Exception {
    }

    @Test
    public void getReceiptBillList() throws Exception {
        assertEquals(0,tool.getReceiptBillList(null).size());
    }

    @Test
    public void submit() throws Exception {
        assertEquals("",tool.submit(null));
    }

}