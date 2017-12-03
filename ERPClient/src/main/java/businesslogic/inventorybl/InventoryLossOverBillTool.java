package main.java.businesslogic.inventorybl;

import main.java.businesslogic.BillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;

import java.util.ArrayList;

public interface InventoryLossOverBillTool extends BillTool{

    public void pass(BillVO billVO);

    public void reject(BillVO billVO);

    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query);
}
