package po;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

//库存赠送单对象
public class InventoryGiftBillPO extends InventoryBillPO {
    //客户信息
    String clientID;
    //赠品编码及数量
    HashMap<String, Integer> gift = new HashMap<>();

    public InventoryGiftBillPO(String ID, int state, Date time,
                               String repository, String clientID, HashMap<String, Integer> gift) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.repository = repository;
        this.clientID = clientID;
        this.gift = gift;
    }

    public String getClientID() {
        return clientID;
    }

    public HashMap<String, Integer> getGift() {
        return gift;
    }

}
