package server_bldriver.inventorydataservicedriver;

import dataservice.inventorydataservice.InventoryLossOverBillDataService;
import org.junit.Test;
import po.GoodsPO;
import po.InventoryLossOverBillPO;
import data.datautility.ResultMessage;
import server_dataservicestub.inventorydataservicestub.InventoryLossOverBillDataServiceStub;

import static org.junit.Assert.*;

public class InventoryLossOverBillDataServiceDriver {

    InventoryLossOverBillPO inventoryOverLossBillPO=new InventoryLossOverBillPO("YSD-20171022-00002",0,
            null,"000000002",null);
    GoodsPO goodsPO=new GoodsPO("000000001","商品A","000000001","型号A",100,10.0,14.0,
            11.0,15.0,10,"无");

    InventoryLossOverBillDataService inventoryLossOverBillDataService=new InventoryLossOverBillDataServiceStub();

    @Test
    public void findGoods() throws Exception {
        assertEquals(100,inventoryLossOverBillDataService.findGoods("000000001","商品A").getNumber());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,inventoryLossOverBillDataService.insert(inventoryOverLossBillPO));
    }

    @Test
    public void getState() throws Exception {
        assertEquals(0,inventoryLossOverBillDataService.getState("000000001"));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,inventoryLossOverBillDataService.update(inventoryOverLossBillPO));
    }

}