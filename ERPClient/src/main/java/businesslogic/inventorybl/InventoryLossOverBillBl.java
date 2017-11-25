package main.java.businesslogic.inventorybl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.inventoryblservice.InventoryLossOverBillBLService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

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
