package test.java.businesslogic_mocktester.inventoryblmocktester;

import main.java.businesslogic.inventorybl.InventoryLossOverBillTool;
import main.java.businesslogic_mock.inventoryblmock.InventoryLossOverBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryLossOverBillToolMockTester {
    InventoryLossOverBillTool tool=new InventoryLossOverBillToolMock();

    @Test
    public void pass() throws Exception {
    }

    @Test
    public void reject() throws Exception {
    }

    @Test
    public void getInventoryLossOverBillList() throws Exception {
        assertEquals(0,tool.getInventoryLossOverBillList(null).size());
    }

}