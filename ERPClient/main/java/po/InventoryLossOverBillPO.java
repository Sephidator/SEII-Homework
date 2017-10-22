package po;

import java.util.Date;
import java.util.HashMap;

public class InventoryLossOverBillPO extends InventoryBillPO {
    private String userID; //操作员的编号
    private HashMap<String,Integer> overLossList; //溢损列表，商品和其实际库存数量

    public InventoryLossOverBillPO(String ID, int state, Date time,
                                   String userID, HashMap<String, Integer> overLossList) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.userID=userID;
        this.overLossList=overLossList;
    }

    public String getUserID() {
        return userID;
    }

    public HashMap<String, Integer> getOverLossList() {
        return overLossList;
    }
}
