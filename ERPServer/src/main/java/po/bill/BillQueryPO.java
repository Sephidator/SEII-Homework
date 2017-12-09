package main.java.po.bill;

import java.io.Serializable;
import java.util.Date;

public class BillQueryPO implements Serializable {
    public String state; //单据状态
    public Date start; //起始时间
    public Date end; //结束时间
    public String type; //单据类型
    public String operatorID; //操作员
    public String clientID; // 客户

    public BillQueryPO() {
    }

    public BillQueryPO(String state, Date start, Date end, String type, String operatorID, String clientID) {
        this.state = state;
        this.start = start;
        this.end = end;
        this.type = type;
        this.operatorID = operatorID;
        this.clientID = clientID;
    }
}
