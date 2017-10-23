package businesslogicservice.editbillblservice;
import vo.FinanceBillVO;

import java.util.ArrayList;

public interface EditFinanceBillBlService {
    public ArrayList<FinanceBillVO> getFinanceBill(int state);
}