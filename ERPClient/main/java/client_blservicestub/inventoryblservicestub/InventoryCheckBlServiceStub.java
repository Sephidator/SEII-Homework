package client_blservicestub.inventoryblservicestub;

import businesslogicservice.inventoryblservice.InventoryCheckBlService;
import businesslogic.blutility.ResultMessage;
import java.util.Date;

public class InventoryCheckBlServiceStub implements InventoryCheckBlService {
    @Override
    public ResultMessage view(Date beginDate, Date endDate) {
        if(endDate.after(beginDate)){
            return ResultMessage.success;
        }
        else{
            return ResultMessage.failure;
        }
    }
}
