package vo;

import java.util.Date;
import java.util.HashMap;

public class InventoryAlarmBillVO extends InventoryBillVO{
    private HashMap<String,Integer> alarmGoods; //报警单中商品名称和实际数量
    public InventoryAlarmBillVO(String ID, int state,
                                Date time, HashMap<String,Integer> alarmGoods) {
        this.ID = ID;
        this.state = state;
        this.time = time;
    }

    public String toString() {
        return "[单据类型："+repository+"，单据ID：" + ID +", 单据状态：" + state
                + ", 单据建立时间：" + time + ", 商品列表：" + alarmGoods  +  "]";
    }
}
