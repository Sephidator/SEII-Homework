package test.java.unit_test.inventorydataservice_unit_test;

import main.java.dataservice.inventorydataservice.InventoryLossOverBillDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InventoryLossOverBillDataServiceTest {
    InventoryLossOverBillDataService service;

    public InventoryLossOverBillDataServiceTest() throws Exception {
        service = (InventoryLossOverBillDataService) Naming.lookup("rmi://127.0.0.1:7200/InventoryLossOverBillDataService");
    }

    @Test
    public void finds() throws Exception {
        assertEquals(true, service.finds(new BillQueryPO("审批通过", null, null, "库存溢损单", null, null)).size() >= 0);
    }

    @Test
    public void insert() throws Exception {
        ArrayList<InventoryLossOverBillPO> list = service.finds(new BillQueryPO("审批通过", null, null, "库存溢损单", null, null));

        InventoryLossOverBillPO inventoryLossOverBillPO = list.get(0);
        inventoryLossOverBillPO.setState("待审批");

        assertEquals("KCYSD", service.insert(inventoryLossOverBillPO).substring(0, 5));
    }
}