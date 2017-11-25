package main.java.businesslogic.purchasebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;

import java.util.ArrayList;

public interface PurchaseRefundBillTool {
    public ResultMessage pass(PurchaseRefundBillVO bill);
    public ResultMessage reject(PurchaseRefundBillVO bill);
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query);
}
