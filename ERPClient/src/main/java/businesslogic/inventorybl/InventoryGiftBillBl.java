package main.java.businesslogic.inventorybl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.inventoryblservice.InventoryGiftBillBLService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

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
