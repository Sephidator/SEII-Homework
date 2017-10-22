package businesslogicservice.inventoryblservice;

import java.text.ParseException;
import java.util.Date;
import businesslogic.blutility.ResultMessage;
public interface InventoryVerificationBlService {



    public ResultMessage record(Date date) throws ParseException;


}
