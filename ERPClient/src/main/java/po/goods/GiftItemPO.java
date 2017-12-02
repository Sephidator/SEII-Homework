package main.java.po.goods;

import java.io.Serializable;

public class GiftItemPO implements Serializable{
    public String goodsID;
    public int number;

    public GiftItemPO(String goodsID, int number) {
        this.goodsID = goodsID;
        this.number = number;
    }
}
