package test.java.client_presentationdriver.purchaseblservicedriver;

import main.java.businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;
import main.java.client_blservicestub.purchaseblservicestub.PurchaseRefundBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseRefundBillBlServiceDriver {
    PurchaseRefundBillBlService service=new PurchaseRefundBillBlServiceStub();

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
    public void getPurchaseRefundBillList() throws Exception {
        assertEquals(0,service.getPurchaseRefundBillList(null).size());
    }

    @Test
    public void editPurchaseRefundBill() throws Exception {
    }

}