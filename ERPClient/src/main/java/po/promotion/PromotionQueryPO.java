package main.java.po.promotion;

import main.java.po.QueryPO;

import java.util.Date;

public class PromotionQueryPO extends QueryPO {
    public String name; //名称
    public String type; //种类
    public Date time; //日期

    public PromotionQueryPO(String ID,String name, String type, Date time) {
        this.ID=ID;
        this.name = name;
        this.type = type;
        this.time = time;
    }
}
