package client_presentationdriver.inventoryblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.inventoryblservice.InventoryGiftBillBLService;
import client_blservicestub.inventoryblservicestub.InventoryGiftBillBLServiceStub;
import org.junit.Test;
import vo.bill.BillQueryVO;
import vo.bill.inventorybill.InventoryGiftBillVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class InventoryGiftBillBLServiceDriver {

    InventoryGiftBillBLService inventoryGiftBillBLService=new InventoryGiftBillBLServiceStub();
    GoodsQueryVO goodsQueryVO=new GoodsQueryVO();
    InventoryGiftBillVO inventoryGiftBillVO=new InventoryGiftBillVO();
    BillQueryVO billQueryVO=new BillQueryVO();

    @Test
    public void getID() throws Exception {
        assertEquals("YSD-20171022-00002",inventoryGiftBillBLService.getID());
    }

    @Test
    public void getGoodsList() throws Exception {
        ArrayList<GoodsVO> goodsVOS=new ArrayList<GoodsVO>();
        goodsVOS.add(null);
        assertEquals(goodsVOS,inventoryGiftBillBLService.getGoodsList(goodsQueryVO));
    }

    @Test
    public void submit() throws Exception {
        assertEquals(ResultMessage.success,inventoryGiftBillBLService.submit(inventoryGiftBillVO));
    }

    @Test
    public void saveDraft() throws Exception {
        assertEquals(ResultMessage.success,inventoryGiftBillBLService.saveDraft(inventoryGiftBillVO));
    }

    @Test
    public void getInventoryGiftBillList() throws Exception {
        ArrayList<InventoryGiftBillVO> inventoryLossOverBillVOS=new ArrayList<InventoryGiftBillVO>();
        inventoryLossOverBillVOS.add(null);
        assertEquals(inventoryLossOverBillVOS,inventoryGiftBillBLService.getInventoryGiftBillList(billQueryVO));
    }

}