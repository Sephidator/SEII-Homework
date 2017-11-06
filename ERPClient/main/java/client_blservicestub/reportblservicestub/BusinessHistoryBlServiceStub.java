package client_blservicestub.reportblservicestub;

import businesslogicservice.reportblservice.BusinessHistoryBlService;
import vo.bill.BillVO;
import vo.report.BusinessHistoryQueryVO;

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
