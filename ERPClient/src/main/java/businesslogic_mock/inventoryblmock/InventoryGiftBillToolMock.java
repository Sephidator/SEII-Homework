package main.java.businesslogic_mock.inventoryblmock;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.inventorybl.InventoryGiftBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;

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
