package businesslogicservice.editbillblservice;
import businesslogic.blutility.ResultMessage;
public interface EditInventoryBillBlService {



    public ResultMessage choose(String ID);

    public ResultMessage getBill(int state);
}
