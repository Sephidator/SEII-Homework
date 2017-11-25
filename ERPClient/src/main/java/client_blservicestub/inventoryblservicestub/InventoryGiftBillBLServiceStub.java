package main.java.client_blservicestub.inventoryblservicestub;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.inventoryblservice.InventoryGiftBillBLService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryGiftBillBLServiceStub implements InventoryGiftBillBLService {
    @Override
    public String getID() {
        return "YSD-20171022-00002";
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        ArrayList<GoodsVO> goodsVOS=new ArrayList<GoodsVO>();
        goodsVOS.add(null);
        return goodsVOS;
    }

    @Override
    public ResultMessage submit(InventoryGiftBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDraft(InventoryGiftBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query) {
        ArrayList<InventoryGiftBillVO> inventoryGiftBillVOS=new ArrayList<InventoryGiftBillVO>();
        inventoryGiftBillVOS.add(null);
        return inventoryGiftBillVOS;
    }
}
