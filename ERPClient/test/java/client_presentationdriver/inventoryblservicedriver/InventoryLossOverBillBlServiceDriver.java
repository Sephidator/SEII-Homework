package client_presentationdriver.inventoryblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.inventoryblservice.InventoryLossOverBillBlService;
import client_blservicestub.inventoryblservicestub.InventoryLossOverBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryLossOverBillBlServiceDriver {

    InventoryLossOverBillBlService inventoryLossOverBillBlService=new InventoryLossOverBillBlServiceStub();

    @Test
    public void getLossOverID() throws Exception {
        assertEquals("YSD-20171022-00002",inventoryLossOverBillBlService.getLossOverID());
    }

    @Test
    public void getOperator() throws Exception {
        assertEquals("000000002",inventoryLossOverBillBlService.getOperator());
    }

    @Test
    public void insertGoods() throws Exception {
        assertEquals(ResultMessage.success,inventoryLossOverBillBlService.insertGoods("000000002",20));
    }

}