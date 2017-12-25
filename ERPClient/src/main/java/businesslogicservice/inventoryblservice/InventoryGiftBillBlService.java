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

    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception;

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    public String submit(InventoryGiftBillVO bill) throws Exception;

    //public void saveDraft(InventoryGiftBillVO bill) throws Exception;

    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query) throws Exception;

    public void editInventoryGiftBill(InventoryGiftBillVO inventoryGiftBillVO) throws Exception;
}
