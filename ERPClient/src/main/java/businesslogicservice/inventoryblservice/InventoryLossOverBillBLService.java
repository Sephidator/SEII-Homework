package main.java.businesslogicservice.inventoryblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface InventoryLossOverBillBLService {

    public String getID();

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);

    public ResultMessage submit(InventoryLossOverBillVO bill);

    public ResultMessage saveDraft(InventoryLossOverBillVO bill);

    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query);

}
