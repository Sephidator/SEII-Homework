package main.java.businesslogic.purchasebl;

import main.java.businesslogic.BillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;

import java.util.ArrayList;

public interface PurchaseRefundBillTool extends BillTool{
    public void pass(BillVO billVO);
    public void reject(BillVO billVO);
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query);
}
