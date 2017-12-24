package main.java.po.promotion;

import java.io.Serializable;

public class PromotionQueryPO implements Serializable {
    public String name; //名称
    public String type; //种类

    public PromotionQueryPO(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
