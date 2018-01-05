package test.java.client_presentationdriver.inventoryblservicedriver;

import main.java.businesslogicservice.inventoryblservice.InventoryLossOverBillBlService;
import main.java.client_blservicestub.inventoryblservicestub.InventoryLossOverBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryLossOverBillBlServiceDriver {
    InventoryLossOverBillBlService service=new InventoryLossOverBillBlServiceStub();

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(0,service.getGoodsList(null).size());
    }

    @Test
    public void submit() throws Exception {
        assertEquals("",service.submit(null));
    }

    @Test
    public void getInventoryLossOverBillList() throws Exception {
        assertEquals(0,service.getInventoryLossOverBillList(null).size());
    }

    @Test
    public void editInventoryLossOverBill() throws Exception {
    }

}