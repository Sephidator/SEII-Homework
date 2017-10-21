package po;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class InventoryAlarmBillPO extends InventoryBillPO {
    private HashMap<String,Integer> alarmList; //报警单中商品名称和实际数量

    public InventoryAlarmBillPO(String ID, int state,
                                Date time, HashMap<String,Integer> alarmGoods) {
        this.ID = ID;
        this.state = state;
        this.time = time;
    }

    public HashMap<String, Integer> getAlarmList() {
        return alarmList;
    }
}
