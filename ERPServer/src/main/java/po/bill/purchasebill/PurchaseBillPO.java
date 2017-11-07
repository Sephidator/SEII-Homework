package po.bill.purchasebill;

import po.bill.BillPO;
import po.promotion.GoodsItemPO;

import java.util.ArrayList;

public class PurchaseBillPO extends BillPO {
    protected String clientID; // 客户信息
    protected ArrayList<GoodsItemPO> purchaseList; //入库商品列表/出库商品列表
    protected double total; // 总额

    public String getClientID() {
        return clientID;
    }

    public ArrayList<GoodsItemPO> getPurchaseList() {
        return purchaseList;
    }

    public double getTotal() {
        return total;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setPurchaseList(ArrayList<GoodsItemPO> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
