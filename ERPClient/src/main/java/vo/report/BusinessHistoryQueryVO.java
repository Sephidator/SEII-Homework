package main.java.vo.report;

import java.util.Date;

public class BusinessHistoryQueryVO {
    public Date start;
    public Date end;
    public String type;
    public String client;
    public String operator;

    public BusinessHistoryQueryVO(Date start, Date end, String type, String client, String operator) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.client = client;
        this.operator = operator;
    }

    public BusinessHistoryQueryVO() {
    }

}
