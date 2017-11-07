package server_bldriver.inventorydataservicedriver;

import data.datautility.ResultMessage;
import dataservice.inventorydataservice.InventoryLossOverBillDataService;
import org.junit.Test;
import po.bill.BillQueryPO;
import po.bill.inventorybill.InventoryLossOverBillPO;
import server_dataservicestub.inventorydataservicestub.InventoryLossOverBillDataServiceStub;

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