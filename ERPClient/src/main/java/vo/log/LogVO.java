package main.java.vo.log;

import main.java.vo.user.UserVO;
import java.util.Date;

/*
 * 操作日志数据
 * @author:
 * @version:
 */

public class LogVO{

    private UserVO operator;//执行动作的操作人

    private String action;//发生的动作

    private Date time;//执行动作的时间

    public LogVO() {
    }

    public LogVO(UserVO operator, String action, Date time) {
        this.operator = operator;
        this.action = action;
        this.time = time;
    }

    public UserVO getOperator() {
        return operator;
    }

    public String getAction() {
        return action;
    }

    public Date getTime() {
        return time;
    }

    public void setOperator(UserVO operator) {
        this.operator = operator;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

