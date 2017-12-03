package main.java.vo.bill.inventorybill;

import main.java.businesslogic.clientbl.ClientBl;
import main.java.businesslogic.clientbl.ClientTool;
import main.java.businesslogic.inventorybl.InventoryGiftBillBl;
import main.java.businesslogic.inventorybl.InventoryGiftBillTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.bill.inventorybill.InventoryGiftBillPO;
import main.java.po.goods.GiftItemPO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GiftItemVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

public class InventoryGiftBillVO extends InventoryBillVO {
    private ClientVO client;////客户信息
    private ArrayList<GiftItemVO> giftList;// 赠品列表

    public InventoryGiftBillVO() {
    }

    public InventoryGiftBillVO(String state, Date time, UserVO operator, String comment, double total, ArrayList<GiftItemVO> giftList){
        this.state = state;
        this.time = time;
        this.type = "库存赠送单";
        this.operator = operator;
        this.comment = comment;
        this.client=client;
        this.giftList=giftList;
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

        inventoryGiftBillPO.setClient(this.client.getID());

        ArrayList<GiftItemPO> giftItemPOS=new ArrayList<>();
        for(GiftItemVO giftItemVO:this.giftList){
            giftItemPOS.add(giftItemVO.getGiftItemPO());
        }
        inventoryGiftBillPO.setGiftList(giftItemPOS);

        return inventoryGiftBillPO;
    }

    public InventoryGiftBillVO(InventoryGiftBillPO inventoryGiftBillPO){
        this.ID=inventoryGiftBillPO.getID();
        this.visible=inventoryGiftBillPO.isVisible();
        this.state=inventoryGiftBillPO.getState();
        this.time=inventoryGiftBillPO.getTime();
        this.comment=inventoryGiftBillPO.getComment();

        ClientTool clientTool=new ClientBl();
        ClientQueryVO clientQueryVO=new ClientQueryVO(inventoryGiftBillPO.getClient(),null);
        this.client=clientTool.getClientList(clientQueryVO).get(0);

        UserTool userTool=new UserBl();
        UserQueryVO userQueryVO=new UserQueryVO(inventoryGiftBillPO.getID(),null,null);
        this.operator=userTool.getUserList(userQueryVO).get(0);

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
}
