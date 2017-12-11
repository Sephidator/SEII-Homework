package main.java.po.bill.salebill;

import java.io.Serializable;
import java.util.Date;

public class SaleTradeBillQueryPO implements Serializable {
    public Date start; //起始时间
    public Date end; //结束时间
    public String goodsName;// 商品
    public String client;// 客户
    public String salesman;//业务员

    public SaleTradeBillQueryPO(Date start, Date end, String goodsName, String client, String salesman) {
        this.start = start;
        this.end = end;
        this.goodsName = goodsName;
        this.client = client;
        this.salesman = salesman;
    }
}