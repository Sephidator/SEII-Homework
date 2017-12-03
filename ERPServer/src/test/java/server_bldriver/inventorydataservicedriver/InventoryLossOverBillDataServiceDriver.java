package test.java.server_bldriver.inventorydataservicedriver;

import main.java.dataservice.inventorydataservice.InventoryLossOverBillDataService;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;
import main.java.server_dataservicestub.inventorydataservicestub.InventoryLossOverBillDataServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryLossOverBillDataServiceDriver {
    InventoryLossOverBillDataService service=new InventoryLossOverBillDataServiceStub();

    @Test
    public void find() throws Exception {
        assertEquals(1,service.find(null).size());
    }

    @Test
    public void insert() throws Exception {
        assertEquals("KCYSD-20171212-12345",service.insert(new InventoryLossOverBillPO()));
    }

}