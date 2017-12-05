package main.java.businesslogicservice.reportblservice;

import main.java.vo.bill.BillVO;
import main.java.vo.report.BusinessHistoryQueryVO;

import java.util.ArrayList;

public interface BusinessHistoryBlService {
    public ArrayList<BillVO> getBillList(BusinessHistoryQueryVO query) throws Exception;

    public String reverse(BillVO billVO) throws Exception;//红冲功能，只允许红冲收款单和付款单，红冲收款单时生成一张付款单并标注

}
