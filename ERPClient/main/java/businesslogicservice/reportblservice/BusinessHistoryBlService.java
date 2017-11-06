package businesslogicservice.reportblservice;

import vo.bill.BillVO;
import vo.report.BusinessHistoryQueryVO;

import java.util.ArrayList;

public interface BusinessHistoryBlService {
    public ArrayList<BillVO> getBillList(BusinessHistoryQueryVO query);
}
