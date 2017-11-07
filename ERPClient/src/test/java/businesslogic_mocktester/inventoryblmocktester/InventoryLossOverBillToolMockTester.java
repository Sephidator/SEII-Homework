package businesslogic_mocktester.inventoryblmocktester;

import businesslogic.blutility.ResultMessage;
import businesslogic.inventorybl.InventoryLossOverBillTool;
import businesslogic_mock.inventoryblmock.InventoryLossOverBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryLossOverBillToolMockTester {

    InventoryLossOverBillTool inventoryLossOverBillTool=new InventoryLossOverBillToolMock();

    @Test
    public void pass() throws Exception {
        assertEquals(ResultMessage.success,inventoryLossOverBillTool.pass(null));
    }

    @Test
    public void reject() throws Exception {
        assertEquals(ResultMessage.success,inventoryLossOverBillTool.reject(null));
    }

    @Test
    public void getInventoryLossOverBillList() throws Exception {
        assertEquals(1,inventoryLossOverBillTool.getInventoryLossOverBillList(null).size());
    }

}