package client_blservicestub.editbillblservicestub;

import businesslogicservice.editbillblservice.EditInventoryBillBlService;
import businesslogic.blutility.ResultMessage;
public class EditInventoryBillBlServiceStub implements EditInventoryBillBlService{
    @Override
    public ResultMessage choose(String ID) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage getBill(int state) {
        return ResultMessage.success;
    }
}
