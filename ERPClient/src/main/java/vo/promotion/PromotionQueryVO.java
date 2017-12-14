package main.java.vo.promotion;

import main.java.po.promotion.PromotionQueryPO;

import java.util.Date;

public class PromotionQueryVO{
    public String name; //名称
    public String type; //种类

    public PromotionQueryVO(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /*得到PromotionQueryPO*/
    public PromotionQueryPO getPromotionQueryPO(){
        PromotionQueryPO promotionQueryPO = new PromotionQueryPO(this.name,this.type);
        return promotionQueryPO;
    }
}
