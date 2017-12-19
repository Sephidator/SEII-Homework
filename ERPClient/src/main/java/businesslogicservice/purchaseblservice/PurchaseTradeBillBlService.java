package main.java.businesslogicservice.purchaseblservice;

import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface PurchaseTradeBillBlService {

    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query) throws Exception;

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    public String submit(PurchaseTradeBillVO bill) throws Exception;

    //public void saveDraft(PurchaseTradeBillVO bill) throws Exception;

    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query) throws Exception;

    public void editPurchaseTradeBill(PurchaseTradeBillVO purchaseTradeBillVO) throws Exception;
}
