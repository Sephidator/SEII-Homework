package businesslogic.reportbl;

import businesslogicservice.reportblservice.BusinessHistoryBlService;
import vo.bill.BillVO;
import vo.report.BusinessHistoryQueryVO;

import java.util.ArrayList;

public class BusinessHistoryBl implements BusinessHistoryBlService {
    @Override
    public ArrayList<BillVO> getBillList(BusinessHistoryQueryVO query) {
        return null;
    }
}
