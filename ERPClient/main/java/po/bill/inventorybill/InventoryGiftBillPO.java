package po.bill.inventorybill;

import po.client.ClientPO;
import po.promotion.GiftItemPO;

import java.util.ArrayList;
import java.util.Date;

public class InventoryGiftBillPO extends InventoryBillPO {
    private ClientPO client;////客户信息
    private ArrayList<GiftItemPO> giftList;// 赠品列表

    public InventoryGiftBillPO() {
    }

    public InventoryGiftBillPO(String ID, String state, Date time, String type, String operatorID, String comment, double total, ArrayList<GiftItemPO> giftList){
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operatorID = operatorID;
        this.comment = comment;
        this.client=client;
        this.giftList=giftList;
    }

    public ClientPO getClient() {
        return client;
    }

    public ArrayList<GiftItemPO> getGiftList() {
        return giftList;
    }

    public void setClient(ClientPO client) {
        this.client = client;
    }

    public void setGiftList(ArrayList<GiftItemPO> giftList) {
        this.giftList = giftList;
    }
}
