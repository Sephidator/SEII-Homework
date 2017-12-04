package main.java.po.goods;

import java.io.Serializable;

public class GoodsItemPO implements Serializable{
    public String goodsID;
    public int number;
    public double price;//商品单价

    public GoodsItemPO(String goodsID, int number, double price) {
        this.goodsID = goodsID;
        this.number = number;
        this.price = price;
    }
}