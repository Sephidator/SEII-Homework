package businesslogic.inventorybl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.inventoryblservice.InventoryLossOverBillBLService;
import vo.bill.BillQueryVO;
import vo.bill.inventorybill.InventoryLossOverBillVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryLossOverBillBl implements InventoryLossOverBillBLService,InventoryLossOverBillTool {
    @Override
    public ResultMessage pass(InventoryLossOverBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage reject(InventoryLossOverBillVO bill) {
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
    public ResultMessage submit(InventoryLossOverBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage saveDraft(InventoryLossOverBillVO bill) {
        return null;
    }

    @Override
    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query) {
        return null;
    }
}
