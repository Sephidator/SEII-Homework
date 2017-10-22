package businesslogicservice.saleblservice;

import businesslogic.blutility.ResultMessage;
import vo.*;

import java.util.ArrayList;

public interface SaleTradeBillBlService {
    public String getID ();
    public double calculateTotal(ArrayList<GoodsVO> goodsList);
    public double getVoucher(PromotionVO promotion);
    public ResultMessage submit(SaleTradeBillVO bill);
    public ResultMessage saveDraft(SaleTradeBillVO bill);
}
