package test.java.server_bldriver.inventorydataservicedriver;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;
import org.junit.Test;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;
import main.java.server_dataservicestub.inventorydataservicestub.InventoryGiftBillDataServiceStub;

import static org.junit.Assert.*;

public class InventoryGiftBillDataServiceDriver {

    InventoryGiftBillDataService inventoryGiftBillDataService=new InventoryGiftBillDataServiceStub();
    InventoryGiftBillPO inventoryGiftBillPO=new InventoryGiftBillPO();
    BillQueryPO billQueryPO=new BillQueryPO();

    @Test
    public void find() throws Exception {
        inventoryGiftBillPO.setClient(null);
        assertEquals(inventoryGiftBillPO.getClient(),inventoryGiftBillDataService.find(billQueryPO).getClient());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,inventoryGiftBillDataService.insert(inventoryGiftBillPO));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,inventoryGiftBillDataService.update(inventoryGiftBillPO));
    }

}