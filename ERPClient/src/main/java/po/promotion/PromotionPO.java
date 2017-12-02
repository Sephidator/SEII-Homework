package main.java.po.promotion;

import main.java.po.PO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PromotionPO extends PO {
    protected String name; //促销策略名称

    protected String type; //促销策略类型

    protected Date start; //促销策略起始时间

    protected Date end; //促销策略截止时间


    public PromotionPO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

}
