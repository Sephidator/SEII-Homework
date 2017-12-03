package main.java.businesslogic.purchasebl;

import main.java.businesslogic.BillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;

import java.util.ArrayList;

public interface PurchaseTradeBillTool extends BillTool{
    public void pass(BillVO billVO);
    public void reject(BillVO billVO);
    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query);
}
