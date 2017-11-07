package businesslogic_mock.inventoryblmock;

import businesslogic.blutility.ResultMessage;
import businesslogic.inventorybl.InventoryLossOverBillTool;
import vo.bill.BillQueryVO;
import vo.bill.inventorybill.InventoryLossOverBillVO;

import java.util.ArrayList;

public class InventoryLossOverBillToolMock implements InventoryLossOverBillTool {
    @Override
    public ResultMessage pass(InventoryLossOverBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage reject(InventoryLossOverBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query) {
        ArrayList<InventoryLossOverBillVO> inventoryLossOverBillVOS=new ArrayList<>();
        inventoryLossOverBillVOS.add(new InventoryLossOverBillVO());
        return inventoryLossOverBillVOS;
    }
}
