package client_presentationdriver.purchaseblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;
import client_blservicestub.purchaseblservicestub.PurchaseRefundBillBlStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseRefundBillDriver {
    PurchaseRefundBillBlService service=new PurchaseRefundBillBlStub();

    @Test
    public void getID() throws Exception {
        assertEquals("123",service.getID());
    }

    @Test
    public void getSupplierList() throws Exception {
        assertEquals(1,service.getSupplierList(null).size());
    }

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(1,service.getGoodsList(null).size());
    }

    @Test
    public void submit() throws Exception {
        assertEquals(ResultMessage.success,service.submit(null));
    }

    @Test
    public void saveDraft() throws Exception {
        assertEquals(ResultMessage.success,service.saveDraft(null));
    }

    @Test
    public void getPurchaseRefundBillList() throws Exception {
        assertEquals(1,service.getPurchaseRefundBillList(null).size());
    }

}