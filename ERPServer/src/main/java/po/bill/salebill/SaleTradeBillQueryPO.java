package main.java.po.bill.salebill;

import main.java.po.QueryPO;

import java.util.Date;

public class SaleTradeBillQueryPO extends QueryPO {
    public Date start; //起始时间
    public Date end; //结束时间
    public String goodsName;// 商品
    public String salesman;//业务员

    public SaleTradeBillQueryPO(String ID, Date start, Date end, String goodsName, String salesman) {
        this.ID = ID;
        this.start = start;
        this.end = end;
        this.goodsName = goodsName;
        this.salesman = salesman;
    }
}