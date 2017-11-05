package po.bill;

import java.util.Date;

public class BillPO {
    protected String ID; //单据编号
    protected String state; //单据状态
    protected Date time; //创建时间
    protected String type; //单据类型
    protected String operatorID;//操作员
    protected String comment; // 备注

    public String getID() {
        return ID;
    }

    public String getState() {
        return state;
    }

    public Date getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public String getComment() {
        return comment;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
