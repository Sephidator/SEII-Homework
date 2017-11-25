package main.java.businesslogic.inventorybl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;

import java.util.ArrayList;

public interface InventoryGiftBillTool {

    public ResultMessage pass(InventoryGiftBillVO bill);

    public ResultMessage reject(InventoryGiftBillVO bill);

    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query);
}
