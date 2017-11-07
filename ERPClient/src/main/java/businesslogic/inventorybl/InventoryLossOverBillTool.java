package businesslogic.inventorybl;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.inventorybill.InventoryLossOverBillVO;

import java.util.ArrayList;

public interface InventoryLossOverBillTool {

    public ResultMessage pass(InventoryLossOverBillVO bill);

    public ResultMessage reject(InventoryLossOverBillVO bill);

    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query);
}
