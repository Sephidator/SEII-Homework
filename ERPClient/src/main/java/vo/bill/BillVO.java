package main.java.vo.bill;

import main.java.businesslogic.BillTool;
import main.java.vo.VO;
import main.java.vo.user.UserVO;

import java.util.Date;

public class BillVO extends VO {
    protected String state; //单据状态
    protected Date time; //创建时间
    protected String type; //单据类型
    protected UserVO operator;//操作员
    protected String comment; // 备注

    public Date getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public UserVO getOperator() {
        return operator;
    }

    public String getComment() {
        return comment;
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

    public void setOperator(UserVO operator) {
        this.operator = operator;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /*实现待定*/
    public BillTool getTool(){
        return null;
    }
}
