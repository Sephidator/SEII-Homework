package test.java.unit_test.purchasebltest;

import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import main.java.client_blservicestub.purchaseblservicestub.PurchaseTradeBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PurchaseTradeBillBlServiceTest {
    PurchaseTradeBillBlService service=new PurchaseTradeBillBlServiceStub();

    @Test
    public void getSupplierList() throws Exception {
        assertEquals(0,service.getSupplierList(null).size());
    }

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(0,service.getGoodsList(null).size());
    }

    @Test
    public void submit() throws Exception {
        assertEquals("",service.submit(null));
    }

    @Test
    public void getPurchaseTradeBillList() throws Exception {
        assertEquals(0,service.getPurchaseTradeBillList(null).size());
    }

    @Test
    public void editPurchaseTradeBill() throws Exception {
    }

}