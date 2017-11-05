package vo.bill;

import vo.user.UserVO;

import java.util.Date;

public class BillVO {
    protected String ID; //单据编号
    protected String state; //单据状态
    protected Date time; //创建时间
    protected String type; //单据类型
    protected UserVO operator;//操作员
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

    public UserVO getOperator() {
        return operator;
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

    public void setOperator(UserVO operator) {
        this.operator = operator;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}