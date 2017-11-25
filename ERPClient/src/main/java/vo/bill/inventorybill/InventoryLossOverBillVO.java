package main.java.vo.bill.inventorybill;

import main.java.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

public class InventoryLossOverBillVO extends InventoryBillVO {
    private ArrayList<LossOverItemVO> lossOverList; //商品溢损列表

    public InventoryLossOverBillVO() {
    }

    public InventoryLossOverBillVO(String ID, String state, Date time, String type, UserVO operator, String comment, ArrayList<LossOverItemVO> lossOverList){
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operator = operator;
        this.comment = comment;
        this.lossOverList=lossOverList;
    }

    public ArrayList<LossOverItemVO> getLossOverList() {
        return lossOverList;
    }

    public void setLossOverList(ArrayList<LossOverItemVO> lossOverList) {
        this.lossOverList = lossOverList;
    }
}
