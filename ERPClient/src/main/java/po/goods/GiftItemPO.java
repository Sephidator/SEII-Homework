package main.java.po.goods;

import java.io.Serializable;

public class GiftItemPO implements Serializable {
    public String goodsID;
    public int number;
    public double price;

    public GiftItemPO(String goodsID, int number, double price) {
        this.goodsID = goodsID;
        this.number = number;
        this.price = price;
    }
}
