package main.java.vo.promotion;

import main.java.vo.QueryVO;

import java.util.Date;

public class PromotionQueryVO extends QueryVO{
    public String name; //名称
    public String type; //种类
    public Date time; //日期

    public PromotionQueryVO(String ID,String name, String type, Date time) {
        this.ID=ID;
        this.name = name;
        this.type = type;
        this.time = time;
    }
}
