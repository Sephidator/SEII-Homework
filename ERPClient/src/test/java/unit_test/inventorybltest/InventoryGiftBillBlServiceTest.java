package test.java.unit_test.inventorybltest;

import main.java.businesslogicfactory.inventoryblfactory.InventoryGiftBillBlFactory;
import main.java.businesslogicservice.inventoryblservice.InventoryGiftBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryGiftBillBlServiceTest {
    InventoryGiftBillBlService service= InventoryGiftBillBlFactory.getService();

    @Test
    public void getClientList() throws Exception {
        assertEquals(true,service.getClientList(null).size()>=0);
    }

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(true,service.getGoodsList(null).size()>=0);
    }

    @Test
    public void submit() throws Exception {
        assertEquals("KCZSD",service.submit(new InventoryGiftBillVO()).substring(0,5));
    }

    @Test
    public void getInventoryGiftBillList() throws Exception {
        assertEquals(true,service.getInventoryGiftBillList(
                new BillQueryVO("审批通过",null,null,"库存赠送单",null,null)).size()>=0);
    }

}