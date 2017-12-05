package main.java.businesslogic.inventorybl;

import main.java.businesslogic.BillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;

import java.util.ArrayList;

public interface InventoryGiftBillTool extends BillTool{

    public void pass(BillVO billVO)throws Exception;

    public void reject(BillVO billVO)throws Exception;

    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query)throws Exception;
}
