package main.java.client_blservicestub.reportblservicestub;

import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;
import main.java.vo.bill.BillVO;
import main.java.vo.report.BusinessHistoryQueryVO;

import java.util.ArrayList;

public class BusinessHistoryBlServiceStub implements BusinessHistoryBlService {
    @Override

    public ArrayList<BillVO> getBillList(BusinessHistoryQueryVO query) {
        ArrayList<BillVO> list = new ArrayList<>();
        list.add(new BillVO());
        list.add(new BillVO());
        return list;
    }
}
