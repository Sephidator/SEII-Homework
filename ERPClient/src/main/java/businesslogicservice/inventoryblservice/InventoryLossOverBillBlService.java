package main.java.businesslogicservice.inventoryblservice;

import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryLossOverBillVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InventoryLossOverBillBlService {

    ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    String submit(InventoryLossOverBillVO bill) throws Exception;

    //public void saveDraft(InventoryLossOverBillVO bill) throws Exception;

    ArrayList<InventoryLossOverBillVO> getInventoryLossOverBillList(BillQueryVO query) throws Exception;

    void editInventoryLossOverBill(InventoryLossOverBillVO inventoryLossOverBillVO) throws Exception;
}
