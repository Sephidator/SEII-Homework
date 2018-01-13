package test.java.unit_test.purchasebltest;

import main.java.businesslogicfactory.purchaseblfactory.PurchaseTradeBillBlFactory;
import main.java.businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import main.java.client_blservicestub.purchaseblservicestub.PurchaseTradeBillBlServiceStub;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PurchaseTradeBillBlServiceTest {
    PurchaseTradeBillBlService service= PurchaseTradeBillBlFactory.getService();

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
        assertEquals("JHD",service.submit(new PurchaseTradeBillVO()).substring(0,3));
    }

    @Test
    public void getPurchaseTradeBillList() throws Exception {
        assertEquals(true,service.getPurchaseTradeBillList(
                new BillQueryVO("审批通过",null,null,"进货单",null,null)).size()>=0);
    }

}