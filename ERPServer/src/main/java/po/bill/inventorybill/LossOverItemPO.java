package main.java.po.bill.inventorybill;

import java.io.Serializable;

public class LossOverItemPO implements Serializable {
    public String goodsID;// 报溢损的商品
    public double price;//商品的单价
    public int goodsNumber;// 商品在系统中的数量
    public int actualNumber;//商品的实际数量

    public LossOverItemPO(String goodsID, double price, int goodsNumber, int actualNumber) {
        this.goodsID = goodsID;
        this.price = price;
        this.goodsNumber = goodsNumber;
        this.actualNumber = actualNumber;
    }
}
