package businesslogicservice.reportblservice;

import vo.bill.BillVO;

import java.util.ArrayList;
import java.util.Date;

public interface BusinessHistoryBlService {
    ArrayList<BillVO> filter(int state, Date start, Date end, String type, String client, String operator, String repository);
}

