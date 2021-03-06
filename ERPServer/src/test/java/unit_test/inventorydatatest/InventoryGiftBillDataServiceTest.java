package test.java.unit_test.inventorydatatest;

import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.client.ClientPO;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;
import org.junit.Test;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;

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
        UserPO userPO = ((UserDataService) Naming.lookup("rmi://127.0.0.1:7200/UserDataService")).finds(new UserQueryPO("", "库存管理人员")).get(0);
        ArrayList<ClientPO> list = ((ClientDataService) Naming.lookup("rmi://127.0.0.1:7200/ClientDataService")).finds(null);

        ClientPO clientPO = new ClientPO();
        for (ClientPO temp : list) {
            if (temp.getCategory().equals("销售商")) {
                clientPO = temp;
                break;
            }
        }

        InventoryGiftBillPO inventoryGiftBillPO = new InventoryGiftBillPO("草稿", new Date(), userPO.getID(), "", clientPO.getID(), new ArrayList<>(), 0);
        assertEquals("KCZSD", service.insert(inventoryGiftBillPO).substring(0, 5));
    }
}