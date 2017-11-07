package businesslogic.inventorybl;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.inventorybill.InventoryGiftBillVO;

import java.util.ArrayList;

public interface InventoryGiftBillTool {

    public ResultMessage pass(InventoryGiftBillVO bill);

    public ResultMessage reject(InventoryGiftBillVO bill);

    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query);
}
