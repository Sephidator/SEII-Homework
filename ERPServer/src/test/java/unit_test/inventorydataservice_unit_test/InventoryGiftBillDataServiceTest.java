package test.java.unit_test.inventorydataservice_unit_test;

import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InventoryGiftBillDataServiceTest {
    InventoryGiftBillDataService service;

    public InventoryGiftBillDataServiceTest() throws Exception {
        service = (InventoryGiftBillDataService) Naming.lookup("rmi://127.0.0.1:7200/InventoryGiftBillDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(new BillQueryPO("审批通过", null, null, "库存赠送单", null, null)).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        ArrayList<InventoryGiftBillPO> list = service.finds(new BillQueryPO("审批通过", null, null, "库存赠送单", null, null));

        InventoryGiftBillPO inventoryGiftBillPO = list.get(0);
        inventoryGiftBillPO.setState("待审批");

        assertEquals("KCZSD", service.insert(inventoryGiftBillPO).substring(0, 5));
    }
}