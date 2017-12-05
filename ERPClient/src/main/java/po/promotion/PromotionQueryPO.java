package main.java.po.promotion;

import java.io.Serializable;
import java.util.Date;

public class PromotionQueryPO implements Serializable {
    public String name; //名称
    public String type; //种类
    public Date time; //日期

    public PromotionQueryPO(String name, String type, Date time) {
        this.name = name;
        this.type = type;
        this.time = time;
    }
}
