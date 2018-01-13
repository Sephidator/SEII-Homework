package test.java.unit_test.inventorybltest;

import main.java.businesslogicservice.inventoryblservice.InventoryVerificationBlService;
import main.java.client_blservicestub.inventoryblservicestub.InventoryVerificationBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryVerificationBlServiceTest {
    InventoryVerificationBlService service=new InventoryVerificationBlServiceStub();

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(0,service.getGoodsList(null).size());
    }

}