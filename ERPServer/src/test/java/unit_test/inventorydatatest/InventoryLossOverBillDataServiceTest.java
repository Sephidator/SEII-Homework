package test.java.unit_test.inventorydatatest;

import main.java.dataservice.inventorydataservice.InventoryLossOverBillDataService;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;

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
        UserPO userPO = ((UserDataService) Naming.lookup("rmi://127.0.0.1:7200/UserDataService")).finds(new UserQueryPO("", "库存管理人员")).get(0);

        InventoryLossOverBillPO inventoryLossOverBillPO = new InventoryLossOverBillPO("草稿", new Date(), userPO.getID(), "", new ArrayList<>());
        assertEquals("KCYSD", service.insert(inventoryLossOverBillPO).substring(0, 5));
    }
}