package main.java.po.bill;

import main.java.po.QueryPO;

import java.util.Date;

public class BillQueryPO extends QueryPO {
    public String state; //单据状态
    public Date start; //起始时间
    public Date end; //结束时间
    public String type; //单据类型
    public String operator; //操作员
    public String client; // 客户

    public BillQueryPO() {
    }

    public BillQueryPO(String ID, String state, Date start, Date end, String type, String operator, String client) {
        this.ID = ID;
        this.state = state;
        this.start = start;
        this.end = end;
        this.type = type;
        this.operator = operator;
        this.client = client;
    }
}
