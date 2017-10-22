package client_blservicestub.inventoryblservicestub;

import businesslogicservice.inventoryblservice.InventoryLossOverBillBlService;
import businesslogic.blutility.ResultMessage;
public class InventoryLossOverBillBlServiceStub implements InventoryLossOverBillBlService{

    @Override
    public String getLossOverID() {
        return "YSD-20171022-00002";
    }

    @Override
    public String getOperator() {
        return "000000002";
    }

    @Override
    public ResultMessage insertGoods(String ID, int actualnum) {
        return ResultMessage.success;
    }
}
