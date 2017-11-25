package test.java.client_presentationdriver.purchaseblservicedriver;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import main.java.client_blservicestub.purchaseblservicestub.PurchaseTradeBillBlStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseTradeBillDriver {
    PurchaseTradeBillBlService service=new PurchaseTradeBillBlStub();

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
    public void getPurchaseTradeBillList() throws Exception {
        assertEquals(1,service.getPurchaseTradeBillList(null).size());
    }

}