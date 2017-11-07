package businesslogic_mocktester.financeblmocktester;

import businesslogic.blutility.ResultMessage;
import businesslogic.financebl.ReceiptBillTool;
import businesslogic_mock.financeblmock.ReceiptBillToolMock;
import org.junit.Test;
import vo.bill.financebill.ReceiptBillVO;

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