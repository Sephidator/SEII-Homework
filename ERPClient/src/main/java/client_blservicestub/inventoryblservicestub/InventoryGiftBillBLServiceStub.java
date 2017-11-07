package client_blservicestub.inventoryblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.inventoryblservice.InventoryGiftBillBLService;
import vo.bill.BillQueryVO;
import vo.bill.inventorybill.InventoryGiftBillVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

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
