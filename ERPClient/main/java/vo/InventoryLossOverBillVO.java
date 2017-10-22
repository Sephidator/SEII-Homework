package vo;

import java.util.ArrayList;
import java.util.Date;

public class InventoryLossOverBillVO extends InventoryBillVO{
    private String userID; //操作员的编号
    private ArrayList<String> goodName; //溢损单中商品名称
    private ArrayList<Integer> numInRepository; //溢损单中商品库存数量
    private ArrayList<Integer> actualNum; //溢损单中商品实际数量

    public InventoryLossOverBillVO(String ID, String userID, int state, Date time,
                                   ArrayList<String> goodsName, ArrayList<Integer> numInRepository, ArrayList<Integer> actualNum) {
        this.ID = ID;
        this.userID = userID;
        this.state = state;
        this.time = time;
        this.goodName = goodsName;
        this.numInRepository = numInRepository;
        this.actualNum = actualNum;
    }

    public String getUserID() {
        return userID;
    }

    public ArrayList<String> getGoodName() {
        return goodName;
    }

    public ArrayList<Integer> getNumInRepository() {
        return numInRepository;
    }

    public ArrayList<Integer> getActualNum() {
        return actualNum;
    }

    public String toString() {
        return "[单据类型："+repository+"，单据ID：" + ID +", 单据状态：" + state+"，操作员编号："+userID
                + ", 单据建立时间：" + time + ", 商品名称：" + goodName +"，商品库存数量："+numInRepository
                +"，商品实际数量："+actualNum + "]";
    }
}
