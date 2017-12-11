package main.java.businesslogicservice.inventoryblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface InventoryLossOverBillBlService {

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    public String submit(InventoryLossOverBillVO bill) throws Exception;

    public void saveDraft(InventoryLossOverBillVO bill) throws Exception;

    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query) throws Exception;

}