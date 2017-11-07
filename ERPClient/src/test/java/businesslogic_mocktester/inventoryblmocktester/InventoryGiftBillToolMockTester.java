package businesslogic_mocktester.inventoryblmocktester;

import businesslogic.blutility.ResultMessage;
import businesslogic.inventorybl.InventoryGiftBillTool;
import businesslogic_mock.inventoryblmock.InventoryGiftBillToolMock;
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