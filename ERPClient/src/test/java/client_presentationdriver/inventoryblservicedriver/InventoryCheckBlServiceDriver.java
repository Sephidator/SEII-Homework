package test.java.client_presentationdriver.inventoryblservicedriver;

import main.java.businesslogicservice.inventoryblservice.InventoryCheckBlService;
import main.java.client_blservicestub.inventoryblservicestub.InventoryCheckBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryCheckBlServiceDriver {
    InventoryCheckBlService service=new InventoryCheckBlServiceStub();

    @Test
    public void getInventoryCheck() throws Exception {
        assertEquals(0,service.getInventoryCheck(null,null).size());
    }

}