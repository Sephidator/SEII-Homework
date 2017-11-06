package businesslogicservice.inventoryblservice;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.inventorybill.InventoryLossOverBillVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public interface InventoryLossOverBillBLService {

    public String getID();

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);

    public ResultMessage submit(InventoryLossOverBillVO bill);

    public ResultMessage saveDraft(InventoryLossOverBillVO bill);

    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query);

}
