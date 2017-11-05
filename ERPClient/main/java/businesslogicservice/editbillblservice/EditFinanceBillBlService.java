package businesslogicservice.editbillblservice;
import vo.bill.financebill.FinanceBillVO;

import java.util.ArrayList;

public interface EditFinanceBillBlService {
    public ArrayList<FinanceBillVO> getFinanceBill(int state);
}