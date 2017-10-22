package server_bldriver.inventorydataservicedriver;

import data.datautility.ResultMessage;
import dataservice.inventorydataservice.InventoryGiftBillDataService;

import org.junit.Test;
import po.InventoryBillPO;
import po.InventoryGiftBillPO;
import server_dataservicestub.inventorydataservicestub.InventoryGiftBillDataServiceStub;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class InventoryGiftBillDataServiceDriver {
    InventoryGiftBillDataService inventoryGiftBillDataService = new InventoryGiftBillDataServiceStub();
    ArrayList<InventoryBillPO> pos = new ArrayList<>();
    InventoryBillPO po = new InventoryGiftBillPO("KCZSD-20140516-64525", 1, new Date(), null, "赵四", null);


    @Test
    public void finds() throws Exception {
        pos.clear();
        pos.add(po);
        assertEquals(inventoryGiftBillDataService.finds().size(), pos.size());
    }

    @Test
    public void find() throws Exception {
        assertEquals(inventoryGiftBillDataService.find("KCZSD-20140516-64525").getClientID(), "赵四");
    }

    @Test
    public void insert() throws Exception {
        assertEquals(inventoryGiftBillDataService.insert(null), ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        assertEquals(inventoryGiftBillDataService.update(null), ResultMessage.success);
    }

    @Test
    public void delete() throws Exception {
        assertEquals(inventoryGiftBillDataService.insert(null), ResultMessage.success);
    }

}