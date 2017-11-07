package businesslogic.purchasebl;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.purchasebill.PurchaseTradeBillVO;

import java.util.ArrayList;

public interface PurchaseTradeBillTool {
    public ResultMessage pass(PurchaseTradeBillVO bill);
    public ResultMessage reject(PurchaseTradeBillVO bill);
    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query);
}
