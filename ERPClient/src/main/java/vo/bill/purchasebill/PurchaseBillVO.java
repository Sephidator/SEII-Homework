package main.java.vo.bill.purchasebill;

import main.java.vo.bill.BillVO;
import main.java.vo.client.ClientVO;
import main.java.vo.promotion.GoodsItemVO;

import java.util.ArrayList;

public class PurchaseBillVO extends BillVO {
    protected ClientVO client; // 客户信息
    protected ArrayList<GoodsItemVO> purchaseList; //入库商品列表/出库商品列表
    protected double total; // 总额

    public ClientVO getClient() {
        return client;
    }

    public ArrayList<GoodsItemVO> getPurchaseList() {
        return purchaseList;
    }

    public double getTotal() {
        return total;
    }

    public void setClient(ClientVO client) {
        this.client = client;
    }

    public void setPurchaseList(ArrayList<GoodsItemVO> purchaseList) {
        purchaseList = purchaseList;
    }

    public void setTotalAmount(double total) {
        this.total = total;
    }

}
