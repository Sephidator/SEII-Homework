package test.java.server_bldriver.inventorydataservicedriver;

import main.java.dataservice.inventorydataservice.InventoryGiftBillDataService;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;
import main.java.server_dataservicestub.inventorydataservicestub.InventoryGiftBillDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryGiftBillDataServiceDriver {
    InventoryGiftBillDataService service=new InventoryGiftBillDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1,service.find(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("KCZSD-20171212-12345",service.insert(new InventoryGiftBillPO()));
    }

}