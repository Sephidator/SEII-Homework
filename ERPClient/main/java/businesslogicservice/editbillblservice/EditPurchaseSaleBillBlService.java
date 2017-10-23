package businesslogicservice.editbillblservice;
import vo.PurchaseBillVO;
import vo.SaleBillVO;

import java.util.ArrayList;

public interface EditPurchaseSaleBillBlService {
    public ArrayList<PurchaseBillVO> getPurchaseBill(int state);
    public ArrayList<SaleBillVO> getSaleBill(int state);
}
