package main.java.businesslogicservice.purchaseblservice;

import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface PurchaseRefundBillBlService {

    ArrayList<ClientVO> getSupplierList(ClientQueryVO query) throws Exception;

    ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    String submit(PurchaseRefundBillVO bill) throws Exception;

    //public void saveDraft(PurchaseRefundBillVO bill) throws Exception;

    ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query) throws Exception;

    void editPurchaseRefundBill(PurchaseRefundBillVO purchaseRefundBillVO) throws Exception;
}
