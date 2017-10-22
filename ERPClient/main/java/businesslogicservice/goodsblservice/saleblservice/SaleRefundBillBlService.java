package businesslogicservice.saleblservice;

import businesslogic.blutility.ResultMessage;
import vo.*;

import java.util.ArrayList;

public interface SaleRefundBillBlService {
    public String getID();
    public double calculateTotal(ArrayList<GoodsVO> goodsList);
    public ResultMessage submit(SaleRefundBillVO bill);
    public ResultMessage saveDraft(SaleRefundBillVO bill);
}
