package main.java.po.bill.salebill;

import main.java.po.promotion.GoodsItemPO;

import java.util.ArrayList;
import java.util.Date;

/**
 * 销售退货单PO类
 * */
public class SaleRefundBillPO extends SaleBillPO {
    private double total; // 总额

    public SaleRefundBillPO(String ID, String state, Date time, String type, String operatorID, String comment, String clientID, String salesmanID, ArrayList<GoodsItemPO> saleList, double total) {
        this.ID = ID;
        this.state = state;
        this.time = time;
        this.type = type;
        this.operatorID = operatorID;
        this.comment = comment;
        this.clientID=clientID;
        this.salesmanID=salesmanID;
        this.saleList=saleList;
        this.total=total;
    }

    public SaleRefundBillPO(){}

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
