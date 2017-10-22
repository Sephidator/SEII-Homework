package businesslogicservice.inventoryblservice;

import vo.GoodsVO;
import vo.InventoryLossOverBillVO;
import businesslogic.blutility.ResultMessage;
import java.util.ArrayList;

public interface InventoryLossOverBillBlService {


    public String getLossOverID();

    public String getOperator();

    public ResultMessage insertGoods(String ID, int actualnum);


}
