package main.java.po.goods;

import java.io.Serializable;

public class GoodsItemPO implements Serializable{
    public String goodsID;
    public int number;

    public GoodsItemPO(String goodsID, int number) {
        this.goodsID = goodsID;
        this.number = number;
    }
}