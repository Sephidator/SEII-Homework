package main.java.po.bill.inventorybill;

import main.java.po.client.ClientPO;
import main.java.po.goods.GiftItemPO;

import java.util.ArrayList;
import java.util.Date;

public class InventoryGiftBillPO extends InventoryBillPO {
    private String clientID;////客户信息
    private ArrayList<GiftItemPO> giftList;// 赠品列表

    public InventoryGiftBillPO() {
    }

    public InventoryGiftBillPO(String state, Date time, String operatorID, String comment, String clientID, ArrayList<GiftItemPO> giftList) {
        this.state = state;
        this.time = time;
        type = "库存赠送单";
        this.operatorID = operatorID;
        this.comment = comment;
        this.clientID = clientID;
        this.giftList = giftList;
    }

    public String getClient() {
        return clientID;
    }

    public ArrayList<GiftItemPO> getGiftList() {
        return giftList;
    }

    public void setClient(String clientID) {
        this.clientID = clientID;
    }

    public void setGiftList(ArrayList<GiftItemPO> giftList) {
        this.giftList = giftList;
    }
}
