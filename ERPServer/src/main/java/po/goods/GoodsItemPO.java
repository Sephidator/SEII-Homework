package main.java.po.goods;

import java.io.Serializable;

public class GoodsItemPO implements Serializable {
    public String goodsID;//商品编号
    public int number;//商品数量
    public double price;//商品单价

    public GoodsItemPO(String goodsID, int number, double price) {
        this.goodsID = goodsID;
        this.number = number;
        this.price = price;
    }
}