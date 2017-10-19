package po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * 销售退货单PO类
 * */
public class SaleRefundBillPO extends SalePO{
    private double totalAmount; // 总额

    public SaleRefundBillPO(String ID, String clientID, String repositoryID, String salesmanID,
                            String operatorID, HashMap<String, Integer> goodsList, double totalAmount,
                            String comment, int state, Date time) {
        this.ID = ID;
        this.clientID = clientID;
        this.repositoryID = repositoryID;
        this.salesmanID = salesmanID;
        this.operatorID = operatorID;
        this.goodsList = goodsList;
        this.totalAmount = totalAmount;
        this.comment = comment;
        this.state = state;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

}
