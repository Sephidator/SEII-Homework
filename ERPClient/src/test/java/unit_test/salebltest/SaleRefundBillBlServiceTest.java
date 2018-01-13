package test.java.unit_test.salebltest;

import main.java.businesslogicfactory.saleblfactory.SaleRefundBillBlFactory;
import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;
import main.java.client_blservicestub.saleblservicestub.SaleRefundBillBlServiceStub;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SaleRefundBillBlServiceTest {
    SaleRefundBillBlService service = SaleRefundBillBlFactory.getService();

    @Test
    public void getSellerList() throws Exception {
        assertEquals(true,service.getSellerList(null).size()>=0);
    }

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(true,service.getGoodsList(null).size()>=0);
    }

    @Test
    public void submit() throws Exception {
        assertEquals("XSTHD",service.submit(new SaleRefundBillVO()).substring(0,5));
    }

    @Test
    public void getSaleRefundBillList() throws Exception {
        assertEquals(true,service.getSaleRefundBillList(
                new BillQueryVO("审批通过",null,null,"销售单",null,null)).size()>=0);
    }

}