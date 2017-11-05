package businesslogicservice.inventoryblservice;

import businesslogic.blutility.ResultMessage;

public interface InventoryLossOverBillBlService {


    public String getLossOverID();

    public String getOperator();

    public ResultMessage insertGoods(String ID, int actualnum);


}
