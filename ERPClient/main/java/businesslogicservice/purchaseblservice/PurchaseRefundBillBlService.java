package businesslogicservice.purchaseblservice;

import businesslogic.blutility.ResultMessage;
import vo.goods.GoodsVO;
import vo.bill.purchasebill.PurchaseTradeBillVO;

import java.util.ArrayList;

public interface PurchaseRefundBillBlService {
    public String getID ();
    public double calculateTotal(ArrayList<GoodsVO> goodsList);
    public ResultMessage submit(PurchaseTradeBillVO bill);
    public ResultMessage saveDraft(PurchaseTradeBillVO bill);
}
