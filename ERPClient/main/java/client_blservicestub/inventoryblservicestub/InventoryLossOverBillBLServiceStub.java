package client_blservicestub.inventoryblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.inventoryblservice.InventoryLossOverBillBLService;
import vo.bill.BillQueryVO;
import vo.bill.inventorybill.InventoryLossOverBillVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryLossOverBillBLServiceStub implements InventoryLossOverBillBLService {
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
    public ResultMessage submit(InventoryLossOverBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDraft(InventoryLossOverBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query) {
        ArrayList<InventoryLossOverBillVO> inventoryLossOverBillVOS=new ArrayList<InventoryLossOverBillVO>();
        inventoryLossOverBillVOS.add(null);
        return inventoryLossOverBillVOS;
    }
}
