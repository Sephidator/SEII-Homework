package main.java.businesslogic.purchasebl;

import main.java.businesslogic.BillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;

import java.util.ArrayList;

public interface PurchaseRefundBillTool extends BillTool{
    void pass(BillVO billVO)throws Exception;
    void reject(BillVO billVO)throws Exception;
    ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query)throws Exception;
}
