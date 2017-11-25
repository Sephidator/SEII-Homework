package main.java.po.log;

import java.util.Date;

public class LogPO{

    private String operatorID;//执行动作的操作人

    private String action;//发生的动作

    private Date time;//执行动作的时间

    public LogPO() {
    }

    public LogPO(String operatorID, String action, Date time) {
        this.operatorID = operatorID;
        this.action = action;
        this.time = time;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public String getAction() {
        return action;
    }

    public Date getTime() {
        return time;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

