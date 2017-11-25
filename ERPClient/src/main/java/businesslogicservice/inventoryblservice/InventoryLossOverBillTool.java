package main.java.businesslogicservice.inventoryblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;

import java.util.ArrayList;

public interface InventoryLossOverBillTool {

    public ResultMessage pass(InventoryLossOverBillVO bill);

    public ResultMessage reject(InventoryLossOverBillVO bill);

    public ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query);
}
