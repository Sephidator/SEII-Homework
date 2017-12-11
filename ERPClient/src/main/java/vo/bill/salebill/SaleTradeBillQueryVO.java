package main.java.vo.bill.salebill;
import java.util.Date;

public class SaleTradeBillQueryVO {
    public Date start; //起始时间
    public Date end; //结束时间
    public String client;// 客户
    public String goodsName;// 商品
    public String salesman;//业务员

    public SaleTradeBillQueryVO(Date start, Date end, String goodsName, String client, String salesman) {
        this.start = start;
        this.end = end;
        this.goodsName = goodsName;
        this.client = client;
        this.salesman = salesman;
    }
}
