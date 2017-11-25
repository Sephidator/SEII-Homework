package main.java.businesslogic.salebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;

import java.util.ArrayList;

public interface SaleRefundBillTool {
    public ResultMessage pass(SaleRefundBillVO bill);
    public ResultMessage reject(SaleRefundBillVO bill);
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query);
}
