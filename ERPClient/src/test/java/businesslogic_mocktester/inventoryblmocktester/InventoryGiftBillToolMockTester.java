package test.java.businesslogic_mocktester.inventoryblmocktester;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.inventorybl.InventoryGiftBillTool;
import main.java.businesslogic_mock.inventoryblmock.InventoryGiftBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryGiftBillToolMockTester {

    InventoryGiftBillTool inventoryGiftBillTool=new InventoryGiftBillToolMock();

    @Test
    public void pass() throws Exception {
        assertEquals(ResultMessage.success,inventoryGiftBillTool.pass(null));
    }

    @Test
    public void reject() throws Exception {
        assertEquals(ResultMessage.success,inventoryGiftBillTool.reject(null));
    }

    @Test
    public void getInventoryGiftBillList() throws Exception {
        assertEquals(1,inventoryGiftBillTool.getInventoryGiftBillList(null).size());
    }

}