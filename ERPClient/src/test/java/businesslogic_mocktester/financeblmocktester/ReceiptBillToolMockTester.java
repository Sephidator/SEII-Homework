package test.java.businesslogic_mocktester.financeblmocktester;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.financebl.ReceiptBillTool;
import main.java.businesslogic_mock.financeblmock.ReceiptBillToolMock;
import org.junit.Test;
import main.java.vo.bill.financebill.ReceiptBillVO;

import javax.xml.transform.Result;

import static org.junit.Assert.*;

public class ReceiptBillToolMockTester {
    ReceiptBillTool receiptBillTool = new ReceiptBillToolMock();
    @Test
    public void pass() throws Exception {
        assertEquals(ResultMessage.success,receiptBillTool.pass(null));
    }

    @Test
    public void reject() throws Exception {
        assertEquals(ResultMessage.success,receiptBillTool.reject(null));
    }

    @Test
    public void getReceiptBillList() throws Exception {
        assertEquals(1,receiptBillTool.getReceiptBillList(null).size());
    }

}