package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 进货单PO类
 * */
public class PurchaseRefundBillPO extends PurchasePO {

    public PurchaseRefundBillPO(String ID, String clientID, String repositoryID, String operatorID,
                                HashMap<String, Integer> goodsList, double totalAmount, String comment, int state){
        this.ID=ID;
        this.clientID=clientID;
        this.repositoryID=repositoryID;
        this.operatorID=operatorID;
        this.goodslist=goodsList;
        this.totalAmount=totalAmount;
        this.comment=comment;
        this.state=state;
    }

}
