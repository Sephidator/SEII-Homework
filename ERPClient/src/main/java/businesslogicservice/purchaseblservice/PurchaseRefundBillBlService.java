package main.java.businesslogicservice.purchaseblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;

import java.util.ArrayList;

public interface PurchaseRefundBillBlService {
    public String getID ();
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query);
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);
    public ResultMessage submit(PurchaseRefundBillVO bill);
    public ResultMessage saveDraft(PurchaseRefundBillVO bill);
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query);
}
