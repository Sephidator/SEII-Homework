package main.java.vo.promotion;

import main.java.po.promotion.PromotionPO;
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

    public PromotionVO(String name, String type, Date start, Date end){
        this.name = name;
        this.type = type;
        this.start = start;
        this.end = end;
    }
    public PromotionVO(){
        this.name = "";
        this.type = "";
        this.start = new Date();
        this.end = new Date();
    }
    /*从PromotionPO转至PromotionVO*/
    public PromotionVO(PromotionPO promotionPO){
        this.setName(promotionPO.getName());
        this.setEnd(promotionPO.getEnd());
        this.setStart(promotionPO.getStart());
        this.setType(promotionPO.getType());
    }

    /*从PromotionVO转至PromotionPO*/
    public PromotionPO getPromotionPO(){
        PromotionPO promotionPO = new PromotionPO();
        promotionPO.setName(this.getName());
        promotionPO.setType(this.getType());
        promotionPO.setStart(this.getStart());
        promotionPO.setEnd(this.getEnd());

        return promotionPO;
    }
}
