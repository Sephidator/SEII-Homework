package main.java.client_blservicestub.inventoryblservicestub;

import main.java.businesslogicservice.inventoryblservice.InventoryLossOverBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryLossOverBillBlServiceStub implements InventoryLossOverBillBlService {

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String submit(InventoryLossOverBillVO bill) throws Exception {
        return "";
    }

    @Override
    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public void editInventoryLossOverBill(InventoryLossOverBillVO inventoryLossOverBillVO) throws Exception {

    }
}
