package main.java.vo.report;

import java.util.Date;

public class SaleDetailQueryVO {
    public Date start;
    public Date end;
    public String goodsName;
    public String client;
    public String salesman;

    public SaleDetailQueryVO(Date start, Date end, String goodsName, String client, String salesman) {
        this.start = start;
        this.end = end;
        this.goodsName = goodsName;
        this.client = client;
        this.salesman = salesman;
    }

    public SaleDetailQueryVO() {
    }
}
