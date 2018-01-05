package main.java.businesslogic.salebl;

import main.java.businesslogic.BillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;

import java.util.ArrayList;

public interface SaleRefundBillTool extends BillTool{
    void pass(BillVO billVO)throws Exception;
    void reject(BillVO billVO)throws Exception;
    ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query)throws Exception;
}
