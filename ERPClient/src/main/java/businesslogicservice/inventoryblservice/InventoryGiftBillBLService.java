package main.java.businesslogicservice.inventoryblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface InventoryGiftBillBLService {

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);

    public String submit(InventoryGiftBillVO bill);

    public void saveDraft(InventoryGiftBillVO bill);

    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query);

}
