package businesslogicservice.editbillblservice;
import vo.InventoryBillVO;

import java.util.ArrayList;

public interface EditInventoryBillBlService {
    public ArrayList<InventoryBillVO> getInventoryBill(int state);
}
