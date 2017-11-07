package businesslogic_mock.inventoryblmock;

import businesslogic.blutility.ResultMessage;
import businesslogic.inventorybl.InventoryGiftBillTool;
import vo.bill.BillQueryVO;
import vo.bill.inventorybill.InventoryGiftBillVO;

import java.util.ArrayList;

public class InventoryGiftBillToolMock implements InventoryGiftBillTool {
    @Override
    public ResultMessage pass(InventoryGiftBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage reject(InventoryGiftBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query) {
        ArrayList<InventoryGiftBillVO> inventoryGiftBillVOS=new ArrayList<>();
        inventoryGiftBillVOS.add(new InventoryGiftBillVO());
        return inventoryGiftBillVOS;
    }
}
