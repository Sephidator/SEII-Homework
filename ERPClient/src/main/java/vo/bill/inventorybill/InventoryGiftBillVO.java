package main.java.vo.bill.inventorybill;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.inventorybl.InventoryGiftBillBl;
import main.java.businesslogic.inventorybl.InventoryGiftBillTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;
import main.java.po.goods.GiftItemPO;
import main.java.presentation.inventoryui.InventoryGiftBillUIController;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

public class InventoryGiftBillVO extends InventoryBillVO {
    private ClientVO client;////客户信息
    private ArrayList<GiftItemVO> giftList;// 赠品列表
    private double total; //赠品总价

    public InventoryGiftBillVO() {
        this.state = "";
        this.time = new Date();
        this.type = "库存赠送单";
        this.operator = new UserVO();
        this.comment = "";
        this.client=new ClientVO();
        this.giftList=new ArrayList<GiftItemVO>();
        this.total = 0;
    }

    public InventoryGiftBillVO(String state, Date time, UserVO operator, String comment,ClientVO client,ArrayList<GiftItemVO> giftList, double total){
        this.state = state;
        this.time = time;
        this.type = "库存赠送单";
        this.operator = operator;
        this.comment = comment;
        this.client=client;
        this.giftList=giftList;
        this.total = total;
    }

    public InventoryGiftBillPO getInventoryGiftBillPO(){
        InventoryGiftBillPO inventoryGiftBillPO=new InventoryGiftBillPO();
        inventoryGiftBillPO.setID(this.ID);
        inventoryGiftBillPO.setVisible(this.visible);
        inventoryGiftBillPO.setComment(this.comment);
        inventoryGiftBillPO.setState(this.state);
        inventoryGiftBillPO.setTime(this.time);
        inventoryGiftBillPO.setType(this.type);

        inventoryGiftBillPO.setOperatorID(this.operator.getID());

        inventoryGiftBillPO.setClientID(this.client.getID());

        ArrayList<GiftItemPO> giftItemPOS=new ArrayList<>();
        for(GiftItemVO giftItemVO:this.giftList){
            giftItemPOS.add(giftItemVO.getGiftItemPO());
        }
        inventoryGiftBillPO.setGiftList(giftItemPOS);

        return inventoryGiftBillPO;
    }

    public InventoryGiftBillVO(InventoryGiftBillPO inventoryGiftBillPO)throws Exception{
        this.ID=inventoryGiftBillPO.getID();
        this.visible=inventoryGiftBillPO.isVisible();
        this.state=inventoryGiftBillPO.getState();
        this.time=inventoryGiftBillPO.getTime();
        this.comment=inventoryGiftBillPO.getComment();
        this.type=inventoryGiftBillPO.getType();

        ClientTool clientTool=new ClientBl();
        this.client=clientTool.find(inventoryGiftBillPO.getClientID());

        UserTool userTool=new UserBl();
        this.operator=userTool.find(inventoryGiftBillPO.getOperatorID());

        ArrayList<GiftItemVO> giftItemVOS=new ArrayList<>();
        for(GiftItemPO giftItemPO:inventoryGiftBillPO.getGiftList()){
            giftItemVOS.add(new GiftItemVO(giftItemPO));
        }
        this.giftList=giftItemVOS;
    }

    public ClientVO getClient() {
        return client;
    }

    public ArrayList<GiftItemVO> getGiftList() {
        return giftList;
    }

    public void setClient(ClientVO client) {
        this.client = client;
    }

    public void setGiftList(ArrayList<GiftItemVO> giftList) {
        this.giftList = giftList;
    }

    public InventoryGiftBillTool getTool(){
        InventoryGiftBillTool inventoryGiftBillTool=new InventoryGiftBillBl();
        return inventoryGiftBillTool;
    }

    public InventoryGiftBillUIController getInfoUIController(){
        return new InventoryGiftBillUIController();
    }
}
