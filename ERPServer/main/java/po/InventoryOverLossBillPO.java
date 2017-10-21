package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class InventoryOverLossBillPO extends InventoryBillPO {
    private String userID; //操作员的编号
    private HashMap<String,Integer> OverLossList; //溢损列表，商品和其实际库存数量

    public InventoryOverLossBillPO(String ID, int state, Date time,
                               String repository, String userID, HashMap<String, Integer> OverLossList) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.repository = repository;
        this.userID=userID;
        this.OverLossList=OverLossList;
    }

    public String getUserID() {
        return userID;
    }

    public HashMap<String, Integer> getOverLossList() {
        return OverLossList;
    }
}
