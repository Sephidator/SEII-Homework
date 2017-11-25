package main.java.businesslogic.reportbl;

import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;
import main.java.vo.bill.BillVO;
import main.java.vo.report.BusinessHistoryQueryVO;

import java.util.ArrayList;

public class BusinessHistoryBl implements BusinessHistoryBlService {
    @Override
    public ArrayList<BillVO> getBillList(BusinessHistoryQueryVO query) {
        return null;
    }
}
