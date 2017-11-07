package po.bill.inventorybill;

import java.util.ArrayList;
import java.util.Date;

public class InventoryLossOverBillPO extends InventoryBillPO {
    private ArrayList<LossOverItemPO> lossOverList; //商品溢损列表

    public InventoryLossOverBillPO() {
    }

    public InventoryLossOverBillPO(String ID, String state, Date time, String type, String operatorID, String comment, ArrayList<LossOverItemPO> lossOverList){
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operatorID = operatorID;
        this.comment = comment;
        this.lossOverList=lossOverList;
    }

    public ArrayList<LossOverItemPO> getLossOverList() {
        return lossOverList;
    }

    public void setLossOverList(ArrayList<LossOverItemPO> lossOverList) {
        this.lossOverList = lossOverList;
    }
}
