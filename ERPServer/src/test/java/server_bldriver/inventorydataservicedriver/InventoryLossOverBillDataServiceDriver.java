package test.java.server_bldriver.inventorydataservicedriver;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.inventorydataservice.InventoryLossOverBillDataService;
import org.junit.Test;
import main.java.po.bill.BillQueryPO;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;
import main.java.server_dataservicestub.inventorydataservicestub.InventoryLossOverBillDataServiceStub;

import static org.junit.Assert.*;

public class InventoryLossOverBillDataServiceDriver {

    InventoryLossOverBillDataService inventoryLossOverBillDataService=new InventoryLossOverBillDataServiceStub();
    InventoryLossOverBillPO inventoryLossOverBillPO=new InventoryLossOverBillPO();
    BillQueryPO billQueryPO=new BillQueryPO();

    @Test
    public void find() throws Exception {

        assertEquals("1",inventoryLossOverBillDataService.find(billQueryPO).getComment());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,inventoryLossOverBillDataService.insert(inventoryLossOverBillPO));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,inventoryLossOverBillDataService.update(inventoryLossOverBillPO));
    }

}