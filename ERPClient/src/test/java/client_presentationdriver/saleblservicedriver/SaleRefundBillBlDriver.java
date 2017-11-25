package test.java.client_presentationdriver.saleblservicedriver;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;
import main.java.client_blservicestub.saleblservicestub.SaleRefundBillBlStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleRefundBillBlDriver {
    SaleRefundBillBlService service=new SaleRefundBillBlStub();

    @Test
    public void getID() throws Exception {
        assertEquals("123",service.getID());
    }

    @Test
    public void getSellerList() throws Exception {
        assertEquals(1,service.getSellerList(null).size());
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
    public void getSaleRefundBillList() throws Exception {
        assertEquals(1,service.getSaleRefundBillList(null).size());
    }

}