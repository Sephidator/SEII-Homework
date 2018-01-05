package test.java.client_presentationdriver.saleblservicedriver;

import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;
import main.java.client_blservicestub.saleblservicestub.SaleRefundBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleRefundBillBlServiceDriver {
    SaleRefundBillBlService service=new SaleRefundBillBlServiceStub();

    @Test
    public void getSellerList() throws Exception {
        assertEquals(0,service.getSellerList(null).size());
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
    public void getSaleRefundBillList() throws Exception {
        assertEquals(0,service.getSaleRefundBillList(null).size());
    }

    @Test
    public void editSaleRefundBill() throws Exception {
    }

}