package po;

import java.util.HashMap;

/**
 * 进货单PO类
 * */
public class PurchaseRefundBillPO extends PurchaseBillPO {

    public PurchaseRefundBillPO(String ID, String clientID, String repositoryID, String operatorID,
                                HashMap<String, Integer> goodsList, double totalAmount, String comment, int state){
        this.ID=ID;
        this.clientID=clientID;
        this.repositoryID=repositoryID;
        this.operatorID=operatorID;
        this.goodsList=goodsList;
        this.totalAmount=totalAmount;
        this.comment=comment;
        this.state=state;
    }

}
