package main.java.po.bill;

import main.java.po.PO;

import java.util.Date;

public class BillPO extends PO {
    protected String state; // 单据状态
    protected Date time; // 创建时间
    protected String type; // 单据类型
    protected String operatorID; // 操作员
    protected String comment; // 备注

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
