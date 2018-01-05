package main.java.businesslogicservice.purchaseblservice;

import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface PurchaseTradeBillBlService {

    ArrayList<ClientVO> getSupplierList(ClientQueryVO query) throws Exception;

    ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    String submit(PurchaseTradeBillVO purchaseTradeBillVO) throws Exception;

    ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query) throws Exception;

    void editPurchaseTradeBill(PurchaseTradeBillVO purchaseTradeBillVO) throws Exception;
}
