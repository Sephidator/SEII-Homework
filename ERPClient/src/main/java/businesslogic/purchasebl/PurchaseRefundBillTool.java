package businesslogic.purchasebl;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.purchasebill.PurchaseRefundBillVO;

import java.util.ArrayList;

public interface PurchaseRefundBillTool {
    public ResultMessage pass(PurchaseRefundBillVO bill);
    public ResultMessage reject(PurchaseRefundBillVO bill);
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query);
}
