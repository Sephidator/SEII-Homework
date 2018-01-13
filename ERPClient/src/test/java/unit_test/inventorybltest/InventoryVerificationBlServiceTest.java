package test.java.unit_test.inventorybltest;

import main.java.businesslogicfactory.inventoryblfactory.InventoryLossOverBillBlFactory;
import main.java.businesslogicfactory.inventoryblfactory.InventoryVerificationBlFactory;
import main.java.businesslogicservice.inventoryblservice.InventoryVerificationBlService;
import main.java.client_blservicestub.inventoryblservicestub.InventoryVerificationBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryVerificationBlServiceTest {
    InventoryVerificationBlService service= InventoryVerificationBlFactory.getService();

    @Test
    public void getGoodsList() throws Exception {
        assertEquals(true,service.getGoodsList(null).size()>=0);
    }

}