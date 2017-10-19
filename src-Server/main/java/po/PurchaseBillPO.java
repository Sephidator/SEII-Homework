package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 进货退货单PO类
 * */
public class PurchaseBillPO extends PurchasePO {

    public PurchaseBillPO(String ID, String clientID, String repositoryID, String operatorID,
                          HashMap<String, Integer> goodslist, double totalAmount, String comment, int state, Date time) {
        this.ID = ID;
        this.clientID = clientID;
        this.repositoryID = repositoryID;
        this.operatorID = operatorID;
        this.goodslist = goodslist;
        this.totalAmount = totalAmount;
        this.comment = comment;
        this.state = state;
        this.time=time;
    }

}
