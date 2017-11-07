package vo.bill.purchasebill;

import vo.client.ClientVO;
import vo.promotion.GoodsItemVO;
import vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * 进货退货单VO类
 * */
public class PurchaseRefundBillVO extends PurchaseBillVO {

    public PurchaseRefundBillVO(String ID, String state, Date time, String type, UserVO operator, String comment, ClientVO client, ArrayList<GoodsItemVO> purchaseList, double total) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operator = operator;
        this.comment = comment;
        this.client=client;
        this.purchaseList=purchaseList;
        this.total=total;
    }

    public PurchaseRefundBillVO(){

    }

}