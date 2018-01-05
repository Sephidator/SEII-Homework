package test.java.businesslogic_mocktester.inventoryblmocktester;

import main.java.businesslogic.inventorybl.InventoryGiftBillTool;
import main.java.businesslogic_mock.inventoryblmock.InventoryGiftBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryGiftBillToolMockTester {
    InventoryGiftBillTool tool=new InventoryGiftBillToolMock();

    @Test
    public void pass() throws Exception {
    }

    @Test
    public void reject() throws Exception {
    }

    @Test
    public void getInventoryGiftBillList() throws Exception {
        assertEquals(0,tool.getInventoryGiftBillList(null).size());
    }

}