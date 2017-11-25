package main.java.businesslogicservice.reportblservice;

import main.java.vo.bill.BillVO;
import main.java.vo.report.BusinessHistoryQueryVO;

import java.util.ArrayList;

public interface BusinessHistoryBlService {
    public ArrayList<BillVO> getBillList(BusinessHistoryQueryVO query);
}
