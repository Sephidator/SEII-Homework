package main.java.businesslogic_mock.inventoryblmock;

import main.java.businesslogic.inventorybl.InventoryLossOverBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;

import java.util.ArrayList;

public class InventoryLossOverBillToolMock implements InventoryLossOverBillTool {

    @Override
    public void pass(BillVO billVO) throws Exception {

    }

    @Override
    public void reject(BillVO billVO) throws Exception {

    }

    @Override
    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }
}
