package client_presentationdriver.inventoryblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.inventoryblservice.InventoryLossOverBillBLService;
import client_blservicestub.inventoryblservicestub.InventoryLossOverBillBLServiceStub;
import org.junit.Test;
import vo.bill.BillQueryVO;
import vo.bill.inventorybill.InventoryLossOverBillVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class InventoryLossOverBillBLServiceDriver {

    InventoryLossOverBillBLService inventoryLossOverBillBLService=new InventoryLossOverBillBLServiceStub();
    GoodsQueryVO goodsQueryVO=new GoodsQueryVO();
    InventoryLossOverBillVO inventoryLossOverBillVO=new InventoryLossOverBillVO();
    BillQueryVO billQueryVO=new BillQueryVO();

    @Test
    public void getID() throws Exception {
        assertEquals("YSD-20171022-00002",inventoryLossOverBillBLService.getID());
    }

    @Test
    public void getGoodsList() throws Exception {
        ArrayList<GoodsVO> goodsVOS=new ArrayList<GoodsVO>();
        goodsVOS.add(null);
        assertEquals(goodsVOS,inventoryLossOverBillBLService.getGoodsList(goodsQueryVO));
    }

    @Test
    public void submit() throws Exception {
        assertEquals(ResultMessage.success,inventoryLossOverBillBLService.submit(inventoryLossOverBillVO));

    }

    @Test
    public void saveDraft() throws Exception {
        assertEquals(ResultMessage.success,inventoryLossOverBillBLService.saveDraft(inventoryLossOverBillVO));
    }

    @Test
    public void getInventoryLossOverBillList() throws Exception {
        ArrayList<InventoryLossOverBillVO> inventoryLossOverBillVOS=new ArrayList<InventoryLossOverBillVO>();
        inventoryLossOverBillVOS.add(null);
        assertEquals(inventoryLossOverBillVOS,inventoryLossOverBillBLService.getInventoryLossOverBillList(billQueryVO));
    }

}