package vo;

import java.util.Date;
import java.util.HashMap;

public class InventoryGiftBillVO extends InventoryBillVO{
    private String clientID;////客户信息
    private HashMap<String, Integer> gift = new HashMap<>();//赠品编码及数量

    public InventoryGiftBillVO(String ID, int state, Date time,
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

    public String toString() {
        return "[单据类型："+repository+"，单据ID：" + ID +", 单据状态：" + state + ", 单据建立时间：" + time
                + ", 客户信息：" + clientID +"，赠品编码及数量："+gift
                + "]";
    }
}
