package main.java.client_blservicestub.inventoryblservicestub;

import main.java.businesslogicservice.inventoryblservice.InventoryGiftBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.inventorybill.InventoryGiftBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public class InventoryGiftBillBlServiceStub implements InventoryGiftBillBlService {

    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String submit(InventoryGiftBillVO bill) throws Exception {
        return "";
    }

    @Override
    public ArrayList<InventoryGiftBillVO> getInventoryGiftBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public void editInventoryGiftBill(InventoryGiftBillVO inventoryGiftBillVO) throws Exception {

    }
}
