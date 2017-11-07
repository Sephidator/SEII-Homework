package businesslogicservice.purchaseblservice;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.purchasebill.PurchaseRefundBillVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public interface PurchaseRefundBillBlService {
    public String getID ();
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query);
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);
    public ResultMessage submit(PurchaseRefundBillVO bill);
    public ResultMessage saveDraft(PurchaseRefundBillVO bill);
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query);
}
