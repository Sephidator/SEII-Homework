package test.java.unit_test.inventorybltest;

import main.java.businesslogicfactory.inventoryblfactory.InventoryCheckBlFactory;
import main.java.businesslogicservice.inventoryblservice.InventoryCheckBlService;
import main.java.client_blservicestub.inventoryblservicestub.InventoryCheckBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryCheckBlServiceTest {
    InventoryCheckBlService service= InventoryCheckBlFactory.getService();

    @Test
    public void getInventoryCheck() throws Exception {
        assertEquals(true,service.getInventoryCheck(null,null).size()>=0);
    }

}