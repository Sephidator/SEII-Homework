package test.java.client_presentationdriver.financeblservicedriver;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.financeblservice.ReceiptBillBlService;
import main.java.client_blservicestub.financeblservicestub.ReceiptBillBlServiceStub;
import org.junit.Test;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.bill.financebill.ReceiptBillVO;
import main.java.vo.client.ClientQueryVO;

import static org.junit.Assert.*;

public class ReceiptBillBlServiceDriver {
    ReceiptBillBlService receiptBillBlService = new ReceiptBillBlServiceStub();
    @Test
    public void getID() throws Exception {
        assertEquals("SKD-20171106-00001",receiptBillBlService.getID());
    }

    @Test
    public void getClientList() throws Exception {
        ClientQueryVO clientQueryVO = new ClientQueryVO();
        assertEquals("Client-20171106-00001",receiptBillBlService.getClientList(clientQueryVO).get(0).getID());
    }

    @Test
    public void getAccountList() throws Exception {
        AccountQueryVO accountQueryVO =  new AccountQueryVO();
        assertEquals("Account-20171106-00001",receiptBillBlService.getAccountList(accountQueryVO).get(0).getID());
    }

    @Test
    public void submit() throws Exception {
        assertEquals(ResultMessage.success,receiptBillBlService.submit(new ReceiptBillVO()));
    }

    @Test
    public void saveDraft() throws Exception {
        assertEquals(ResultMessage.success,receiptBillBlService.submit(new ReceiptBillVO()));
    }

}