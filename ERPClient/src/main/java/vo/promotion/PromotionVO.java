package main.java.vo.promotion;

import java.util.Date;

public class PromotionVO {

    protected String ID;//促销策略编号

    protected String type;//促销策略类型

    protected Date start;//促销策略起始时间

    protected Date end;//促销策略截止时间

    public String getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
