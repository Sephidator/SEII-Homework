package businesslogic.inventorybl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.inventoryblservice.InventoryGiftBillBLService;
import vo.bill.BillQueryVO;
import vo.bill.inventorybill.InventoryGiftBillVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryGiftBillBl implements InventoryGiftBillBLService,InventoryGiftBillTool {
    @Override
    public ResultMessage pass(InventoryGiftBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage reject(InventoryGiftBillVO bill) {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage submit(InventoryGiftBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage saveDraft(InventoryGiftBillVO bill) {
        return null;
    }

    @Override
    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query) {
        return null;
    }
}
