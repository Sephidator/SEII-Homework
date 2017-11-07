package businesslogicservice.inventoryblservice;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.inventorybill.InventoryGiftBillVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public interface InventoryGiftBillBLService {

    public String getID();

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);

    public ResultMessage submit(InventoryGiftBillVO bill);

    public ResultMessage saveDraft(InventoryGiftBillVO bill);

    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query);

}
