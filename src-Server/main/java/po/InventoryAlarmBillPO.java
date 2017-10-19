package po;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class InventoryAlarmBillPO extends BillPO {
    private HashMap<String,Integer> alarmGoods; //报警单中商品名称和实际数量

    public InventoryAlarmBillPO(String ID, int state,
                                Date time, HashMap<String,Integer> alarmGoods) {
        this.ID = ID;
        this.state = state;
        this.time = time;
    }

    public String getID() {
        return ID;
    }

    public int getState() {
        return state;
    }

    public Date getTime() {
        return time;
    }

    public HashMap<String, Integer> getAlarmGoods() {
        return alarmGoods;
    }
}
