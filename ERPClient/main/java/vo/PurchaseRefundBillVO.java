package vo;

import vo.ClientVO;

import java.util.Date;
import java.util.HashMap;

/**
 * 进货退货单VO类
 * */
public class PurchaseRefundBillVO extends PurchaseBillVO {

    public PurchaseRefundBillVO(String ID, ClientVO client, String repositoryID, UserVO operator,
                               HashMap<GoodsVO, Integer> goodsList, double totalAmount, String comment, int state, Date time) {
        this.ID = ID;
        this.client = client;
        this.repositoryID = repositoryID;
        this.operator = operator;
        this.goodsList = goodsList;
        this.totalAmount = totalAmount;
        this.comment = comment;
        this.state = state;
        this.time=time;
    }

    public PurchaseRefundBillVO(){

    }

}