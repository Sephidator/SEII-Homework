package main.java.businesslogicservice.purchaseblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PurchaseRefundBillBlService {

    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query) throws Exception;

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    public String submit(PurchaseRefundBillVO bill) throws Exception;

    //public void saveDraft(PurchaseRefundBillVO bill) throws Exception;

    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query) throws Exception;

    public void editPurchaseRefundBill(PurchaseRefundBillVO purchaseRefundBillVO) throws Exception;
}
