package test.java.unit_test.client_presentationdriver.inventoryblservicedriver;

import main.java.businesslogicservice.inventoryblservice.InventoryGiftBillBlService;
import main.java.client_blservicestub.inventoryblservicestub.InventoryGiftBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryGiftBillBlServiceTest {
    InventoryGiftBillBlService service=new InventoryGiftBillBlServiceStub();

    @Test
    public void getClientList() throws Exception {
        assertEquals(0,service.getClientList(null).size());
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
    public void getInventoryGiftBillList() throws Exception {
        assertEquals(0,service.getInventoryGiftBillList(null).size());
    }

    @Test
    public void editInventoryGiftBill() throws Exception {
    }

}