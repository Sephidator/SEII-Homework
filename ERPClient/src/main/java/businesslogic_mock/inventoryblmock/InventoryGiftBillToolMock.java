package main.java.businesslogic_mock.inventoryblmock;

import main.java.businesslogic.inventorybl.InventoryGiftBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;

import java.util.ArrayList;

public class InventoryGiftBillToolMock implements InventoryGiftBillTool {

    @Override
    public void pass(BillVO billVO) throws Exception {

    }

    @Override
    public void reject(BillVO billVO) throws Exception {

    }

    @Override
    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }
}
