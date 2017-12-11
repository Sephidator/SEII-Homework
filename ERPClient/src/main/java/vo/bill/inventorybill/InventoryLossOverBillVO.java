package main.java.vo.bill.inventorybill;

import main.java.businesslogic.inventorybl.InventoryLossOverBillBl;
import main.java.businesslogic.inventorybl.InventoryLossOverBillTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.inventorybill.InventoryLossOverBillPO;
import main.java.po.bill.inventorybill.LossOverItemPO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

public class InventoryLossOverBillVO extends InventoryBillVO {
    private ArrayList<LossOverItemVO> lossOverList; //商品溢损列表

    public InventoryLossOverBillVO() {
        this.state = "";
        this.time = new Date();
        type="库存溢损单";
        this.operator = new UserVO();
        this.comment = "";
        this.lossOverList=new ArrayList<LossOverItemVO>();
    }

    public InventoryLossOverBillVO(String state, Date time, UserVO operator, String comment, ArrayList<LossOverItemVO> lossOverList){
        this.state = state;
        this.time = time;
        type="库存溢损单";
        this.operator = operator;
        this.comment = comment;
        this.lossOverList=lossOverList;
    }

    public InventoryLossOverBillPO getInventoryLossOverBillPO(){
        InventoryLossOverBillPO inventoryLossOverBillPO=new InventoryLossOverBillPO();
        inventoryLossOverBillPO.setID(this.ID);
        inventoryLossOverBillPO.setVisible(this.visible);
        inventoryLossOverBillPO.setComment(this.comment);
        inventoryLossOverBillPO.setState(this.state);
        inventoryLossOverBillPO.setTime(this.time);
        inventoryLossOverBillPO.setType(this.type);

        ArrayList<LossOverItemPO> lossOverItemPOS=new ArrayList<>();
        for(LossOverItemVO lossOverItemVO:this.lossOverList){
            lossOverItemPOS.add(lossOverItemVO.getLossOverItemPO());
        }
        inventoryLossOverBillPO.setLossOverList(lossOverItemPOS);

        inventoryLossOverBillPO.setOperatorID(this.operator.getID());

        return inventoryLossOverBillPO;

    }

    public InventoryLossOverBillVO(InventoryLossOverBillPO inventoryLossOverBillPO)throws Exception{
        this.ID=inventoryLossOverBillPO.getID();
        this.visible=inventoryLossOverBillPO.isVisible();
        this.comment=inventoryLossOverBillPO.getComment();
        this.state=inventoryLossOverBillPO.getState();
        this.time=inventoryLossOverBillPO.getTime();
        this.type=inventoryLossOverBillPO.getType();

        UserTool userTool=new UserBl();
        this.operator=userTool.find(inventoryLossOverBillPO.getOperatorID());

        ArrayList<LossOverItemVO> lossOverItemVOS=new ArrayList<>();
        for(LossOverItemPO lossOverItemPO:inventoryLossOverBillPO.getLossOverList()){
            lossOverItemVOS.add(new LossOverItemVO(lossOverItemPO));
        }
        this.lossOverList=lossOverItemVOS;
    }

    public ArrayList<LossOverItemVO> getLossOverList() {
        return lossOverList;
    }

    public void setLossOverList(ArrayList<LossOverItemVO> lossOverList) {
        this.lossOverList = lossOverList;
    }

    public InventoryLossOverBillTool getTool(){
        InventoryLossOverBillTool inventoryLossOverBillTool=new InventoryLossOverBillBl();
        return inventoryLossOverBillTool;
    }
}
