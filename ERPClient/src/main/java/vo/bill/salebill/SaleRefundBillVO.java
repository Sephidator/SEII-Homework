package vo.bill.salebill;

import vo.client.ClientVO;
import vo.promotion.GoodsItemVO;
import vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * 销售退货单PO类
 * */
public class SaleRefundBillVO extends SaleBillVO {
    private double total; // 总额

    public SaleRefundBillVO(String ID, String state, Date time, String type, UserVO operator, String comment, ClientVO client, UserVO salesman, ArrayList<GoodsItemVO> saleList, double total) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operator = operator;
        this.comment = comment;
        this.client=client;
        this.salesman=salesman;
        this.saleList=saleList;
        this.total=total;
    }

    public SaleRefundBillVO(){}

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
