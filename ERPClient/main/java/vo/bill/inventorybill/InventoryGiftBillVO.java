package vo.bill.inventorybill;

import vo.client.ClientVO;
import vo.promotion.GiftItemVO;
import vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

public class InventoryGiftBillVO extends InventoryBillVO {
    private ClientVO client;////客户信息
    private ArrayList<GiftItemVO> giftList;// 赠品列表

    public InventoryGiftBillVO() {
    }

    public InventoryGiftBillVO(String ID, String state, Date time, String type, UserVO operator, String comment, double total, ArrayList<GiftItemVO> giftList){
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operator = operator;
        this.comment = comment;
        this.client=client;
        this.giftList=giftList;
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
}
