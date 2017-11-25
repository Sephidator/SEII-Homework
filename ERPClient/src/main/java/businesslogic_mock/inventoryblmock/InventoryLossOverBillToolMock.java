package main.java.businesslogic_mock.inventoryblmock;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.inventorybl.InventoryLossOverBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;

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
