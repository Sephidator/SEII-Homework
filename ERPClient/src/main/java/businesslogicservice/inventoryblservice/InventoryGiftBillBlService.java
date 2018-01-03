package main.java.businesslogicservice.inventoryblservice;

import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InventoryGiftBillBlService {

    ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception;

    ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    String submit(InventoryGiftBillVO bill) throws Exception;

    //public void saveDraft(InventoryGiftBillVO bill) throws Exception;

    ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query) throws Exception;

    void editInventoryGiftBill(InventoryGiftBillVO inventoryGiftBillVO) throws Exception;
}
