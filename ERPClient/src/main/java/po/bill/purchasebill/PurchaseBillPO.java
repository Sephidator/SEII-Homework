package main.java.po.bill.purchasebill;

import main.java.po.bill.BillPO;
import main.java.po.goods.GoodsItemPO;

import java.util.ArrayList;

public class PurchaseBillPO extends BillPO {
    protected String clientID; // 客户信息
    protected ArrayList<GoodsItemPO> purchaseList; //入库商品列表/出库商品列表
    protected double total; // 总额

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public ArrayList<GoodsItemPO> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(ArrayList<GoodsItemPO> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
