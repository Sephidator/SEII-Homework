package businesslogicservice.editbillblservice;
import vo.bill.purchasebill.PurchaseBillVO;
import vo.bill.salebill.SaleBillVO;

import java.util.ArrayList;

public interface EditPurchaseSaleBillBlService {
    public ArrayList<PurchaseBillVO> getPurchaseBill(int state);
    public ArrayList<SaleBillVO> getSaleBill(int state);
}
