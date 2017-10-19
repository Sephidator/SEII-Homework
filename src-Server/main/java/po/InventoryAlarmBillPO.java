package po;

import java.io.Serializable;
import java.util.ArrayList;

public class InventoryAlarmBillPO implements Serializable {

    private String name; //单据名称
    private String ID; //单据的编号
    private int state; //单据状态
    private String time; //单据建立时间
    private ArrayList<String> goodsName; //报警单中商品名称
    private ArrayList<Integer> alarmNum;//报警单中商品报警数量

    public InventoryAlarmBillPO(String name, String ID, int state,
                                String time, ArrayList<String> goodsName, ArrayList<Integer> alarmNum) {
        this.name = name;
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.goodsName = goodsName;
        this.alarmNum = alarmNum;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public int getState() {
        return state;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<String> getGoodsName() {
        return goodsName;
    }

    public ArrayList<Integer> getAlarmNum() {
        return alarmNum;
    }
}
