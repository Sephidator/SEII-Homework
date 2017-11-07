package businesslogic.salebl;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.salebill.SaleRefundBillVO;

import java.util.ArrayList;

public interface SaleRefundBillTool {
    public ResultMessage pass(SaleRefundBillVO bill);
    public ResultMessage reject(SaleRefundBillVO bill);
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query);
}
