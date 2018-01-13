package test.java.unit_test.inventorybltest;

import main.java.businesslogicfactory.inventoryblfactory.InventoryLossOverBillBlFactory;
import main.java.businesslogicservice.inventoryblservice.InventoryLossOverBillBlService;
import main.java.client_blservicestub.inventoryblservicestub.InventoryLossOverBillBlServiceStub;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryLossOverBillBlServiceTest {
    InventoryLossOverBillBlService service= InventoryLossOverBillBlFactory.getService();

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(true,service.getGoodsList(null).size()>=0);
    }

    @Test
    public void submit() throws Exception {
        assertEquals("KCYSD",service.submit(new InventoryLossOverBillVO()).substring(0,5));
    }

    @Test
    public void getInventoryLossOverBillList() throws Exception {
        assertEquals(true,service.getInventoryLossOverBillList(
                new BillQueryVO("审批通过",null,null,"库存溢损单",null,null)).size()>=0);
    }

}