package main.java.businesslogicservice.inventoryblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface InventoryGiftBillBLService {

    public String getID();

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);

    public ResultMessage submit(InventoryGiftBillVO bill);

    public ResultMessage saveDraft(InventoryGiftBillVO bill);

    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query);

}
