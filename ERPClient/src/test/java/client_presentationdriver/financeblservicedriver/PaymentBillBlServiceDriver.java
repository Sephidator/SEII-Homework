package client_presentationdriver.financeblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.financeblservice.PaymentBilllBlService;
import client_blservicestub.financeblservicestub.PaymentBillBlServiceStub;
import org.junit.Test;
import vo.account.AccountQueryVO;
import vo.bill.financebill.PaymentBillVO;
import vo.client.ClientQueryVO;

import static org.junit.Assert.*;

public class PaymentBillBlServiceDriver {
    PaymentBilllBlService paymentBilllBlService = new PaymentBillBlServiceStub();
    @Test
    public void getID() throws Exception {
        assertEquals("FKD-20171106-00001",paymentBilllBlService.getID());
    }

    @Test
    public void getClientList() throws Exception {
        ClientQueryVO clientQueryVO = new ClientQueryVO();
        assertEquals("Client-20171106-00001",paymentBilllBlService.getClientList(clientQueryVO).get(0).getID());
    }

    @Test
    public void getAccountList() throws Exception {
        AccountQueryVO accountQueryVO =  new AccountQueryVO();
        assertEquals("Account-20171106-00001",paymentBilllBlService.getAccountList(accountQueryVO).get(0).getID());
    }

    @Test
    public void submit() throws Exception {
        assertEquals(ResultMessage.success,paymentBilllBlService.submit(new PaymentBillVO()));
    }

    @Test
    public void saveDraft() throws Exception {
        assertEquals(ResultMessage.success,paymentBilllBlService.saveDraft(new PaymentBillVO()));
    }

}