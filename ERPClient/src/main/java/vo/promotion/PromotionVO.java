package main.java.vo.promotion;

import main.java.vo.VO;

import java.util.Date;

public class PromotionVO extends VO {

    protected String name;//促销策略名字

    protected String type;//促销策略类型

    protected Date start;//促销策略起始时间

    protected Date end;//促销策略截止时间

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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
