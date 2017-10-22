package vo;

import java.util.Date;
import java.util.HashMap;

/**
 * 销售退货单PO类
 * */
public class SaleRefundBillVO extends SaleBillVO {
    private double totalAmount; // 总额

    public SaleRefundBillVO(String ID, ClientVO client, String repositoryID, UserVO salesman,
                            UserVO operator, HashMap<GoodsVO, Integer> goodsList, double totalAmount,
                            String comment, int state, Date time) {
        this.ID = ID;
        this.client = client;
        this.repositoryID = repositoryID;
        this.salesman = salesman;
        this.operator = operator;
        this.goodsList = goodsList;
        this.totalAmount = totalAmount;
        this.comment = comment;
        this.state = state;
    }

    public SaleRefundBillVO(){}

    public double getTotalAmount() {
        return totalAmount;
    }

}
