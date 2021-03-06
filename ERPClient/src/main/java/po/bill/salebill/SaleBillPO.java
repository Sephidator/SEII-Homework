package main.java.po.bill.salebill;

import main.java.po.bill.BillPO;
import main.java.po.goods.GoodsItemPO;

import java.util.ArrayList;

public class SaleBillPO extends BillPO {
    protected String clientID; // 客户信息
    protected String salesmanID; // 业务员
    protected ArrayList<GoodsItemPO> saleList; // 出货商品清单/退货商品清单

    public String getClientID() {
        return clientID;
    }

    public String getSalesmanID() {
        return salesmanID;
    }

    public ArrayList<GoodsItemPO> getSaleList() {
        return saleList;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setSalesmanID(String salesmanID) {
        this.salesmanID = salesmanID;
    }

    public void setSaleList(ArrayList<GoodsItemPO> saleList) {
        this.saleList = saleList;
    }
}