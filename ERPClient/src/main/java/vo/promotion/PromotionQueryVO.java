package main.java.vo.promotion;

import java.util.Date;

public class PromotionQueryVO{
    public String name; //名称
    public String type; //种类
    public Date time; //日期

    public PromotionQueryVO(String name, String type, Date time) {
        this.name = name;
        this.type = type;
        this.time = time;
    }
}
