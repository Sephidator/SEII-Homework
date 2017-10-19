package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class InventoryLossOverBillPO extends InventoryBillPO {
    private String userID; //操作员的编号
    private ArrayList<String> goodName; //溢损单中商品名称
    private ArrayList<Integer> numInRepository; //溢损单中商品库存数量
    private ArrayList<Integer> actualNum; //溢损单中商品实际数量

    public InventoryLossOverBillPO(String ID, String userID, int state, Date time,
                                   ArrayList<String> goodsName, ArrayList<Integer> numInRepository, ArrayList<Integer> actualNum) {
        this.ID = ID;
        this.userID = userID;
        this.state = state;
        this.time = time;
        this.goodName = goodsName;
        this.numInRepository = numInRepository;
        this.actualNum = actualNum;
        InventoryGiftBillPO po = new InventoryGiftBillPO();
    }

    public String getID() {
        return ID;
    }

    public String getUserID() {
        return userID;
    }

    public int getState() {
        return state;
    }

    public Date getTime() {
        return time;
    }

    public ArrayList<String> getGoodsName() {
        return goodName;
    }

    public ArrayList<Integer> getNumInRepository() {
        return numInRepository;
    }

    public ArrayList<Integer> getActualNum() {
        return actualNum;
    }
}
