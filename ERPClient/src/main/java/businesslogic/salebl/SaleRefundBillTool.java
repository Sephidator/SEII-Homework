package main.java.businesslogic.salebl;

import main.java.businesslogic.BillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;

import java.util.ArrayList;

public interface SaleRefundBillTool extends BillTool{
    public void pass(BillVO billVO)throws Exception;
    public void reject(BillVO billVO)throws Exception;
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query)throws Exception;
}
