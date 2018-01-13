package test.java.unit_test.purchasebltest;

import main.java.businesslogicfactory.purchaseblfactory.PurchaseRefundBillBlFactory;
import main.java.businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;
import main.java.client_blservicestub.purchaseblservicestub.PurchaseRefundBillBlServiceStub;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PurchaseRefundBillBlServiceTest {
    PurchaseRefundBillBlService service= PurchaseRefundBillBlFactory.getService();

    @Test
    public void getSupplierList() throws Exception {
        assertEquals(true,service.getSupplierList(null).size()>=0);
    }

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(true,service.getGoodsList(null).size()>=0);
    }

    @Test
    public void submit() throws Exception {
        assertEquals("JHTHD",service.submit(new PurchaseRefundBillVO()).substring(0,5));
    }

    @Test
    public void getPurchaseRefundBillList() throws Exception {
        assertEquals(true,service.getPurchaseRefundBillList(
                new BillQueryVO("审批通过",null,null,"进货退货单",null,null)).size()>=0);
    }

    @Test
    public void editPurchaseRefundBill() throws Exception {
    }

}