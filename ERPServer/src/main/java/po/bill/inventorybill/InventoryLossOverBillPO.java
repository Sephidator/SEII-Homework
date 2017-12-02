package main.java.po.bill.inventorybill;

import java.util.ArrayList;
import java.util.Date;

public class InventoryLossOverBillPO extends InventoryBillPO {
    private ArrayList<LossOverItemPO> lossOverList; //商品溢损列表

    public InventoryLossOverBillPO() {
    }

    public InventoryLossOverBillPO(String state, Date time, String operatorID, String comment, ArrayList<LossOverItemPO> lossOverList) {
        this.state = state;
        this.time = time;
        type = "库存溢损单";
        this.operatorID = operatorID;
        this.comment = comment;
        this.lossOverList = lossOverList;
    }

    public ArrayList<LossOverItemPO> getLossOverList() {
        return lossOverList;
    }

    public void setLossOverList(ArrayList<LossOverItemPO> lossOverList) {
        this.lossOverList = lossOverList;
    }
}
