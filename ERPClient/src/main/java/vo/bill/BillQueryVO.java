package main.java.vo.bill;

import main.java.po.bill.BillQueryPO;
import main.java.vo.QueryVO;

import java.util.Date;

public class BillQueryVO extends QueryVO{
    public String state; //单据状态
    public Date start; //起始时间
    public Date end; //结束时间
    public String type; //单据类型
    public String operator;//操作员
    public String client;// 客户

    public BillQueryVO() {
    }

    public BillQueryVO(String ID, String state, Date start, Date end, String type, String operator, String client) {
        this.ID = ID;
        this.state = state;
        this.start = start;
        this.end = end;
        this.type = type;
        this.operator = operator;
        this.client = client;
    }

    public BillQueryPO getBillQueryPO(){
        BillQueryPO billQueryPO=new BillQueryPO(this.ID,this.state,this.start,this.end,this.type,this.operator,this.client);
        return billQueryPO;
    }
}
