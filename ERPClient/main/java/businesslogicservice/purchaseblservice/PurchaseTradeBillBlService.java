package businesslogicservice.purchaseblservice;

import businesslogic.blutility.ResultMessage;
import vo.GoodsVO;
import vo.PurchaseTradeBillVO;

import java.util.ArrayList;

public interface PurchaseTradeBillBlService {
    public String getID ();
    public double calculateTotal(ArrayList<GoodsVO> goodsList);
    public ResultMessage submit(PurchaseTradeBillVO bill);
    public ResultMessage saveDraft(PurchaseTradeBillVO bill);
}
