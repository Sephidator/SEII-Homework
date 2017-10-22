package businesslogicservice.inventoryblservice;

import businesslogic.blutility.ResultMessage;
import java.util.Date;

public interface InventoryCheckBlService {


    public ResultMessage view(Date beginDate, Date endDate);

}
