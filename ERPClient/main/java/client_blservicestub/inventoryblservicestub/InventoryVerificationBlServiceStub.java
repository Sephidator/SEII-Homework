package client_blservicestub.inventoryblservicestub;

import businesslogicservice.inventoryblservice.InventoryVerificationBlService;
import businesslogic.blutility.ResultMessage;
import java.text.ParseException;
import java.util.Date;

public class InventoryVerificationBlServiceStub implements InventoryVerificationBlService{
    @Override
    public ResultMessage record(Date date) throws ParseException {
        return ResultMessage.success;
    }
}
