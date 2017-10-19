package po;

import java.util.Date;
import java.util.HashMap;

/**
 * 进货退货单PO类
 * */
public class PurchaseTradeBillPO extends PurchaseBillPO {

    public PurchaseTradeBillPO(String ID, String clientID, String repositoryID, String operatorID,
                               HashMap<String, Integer> goodsList, double totalAmount, String comment, int state, Date time) {
        this.ID = ID;
        this.clientID = clientID;
        this.repositoryID = repositoryID;
        this.operatorID = operatorID;
        this.goodsList = goodsList;
        this.totalAmount = totalAmount;
        this.comment = comment;
        this.state = state;
        this.time=time;
    }

}
