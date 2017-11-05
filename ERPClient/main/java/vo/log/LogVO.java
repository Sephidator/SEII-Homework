package vo.log;

import vo.user.UserVO;

import java.io.Serializable;
import java.util.Date;

/*
 * 操作日志数据
 * @author:
 * @version:
 */

public class LogVO implements Serializable{

    private UserVO operator;//执行动作的操作人

    private String action;//发生的动作

    private Date time;//执行动作的时间

    public LogVO(UserVO operator, String action, Date time) {
        this.operator = operator;
        this.action = action;
        this.time = time;
    }

    public UserVO getOperator() {
        return operator;
    }

    public void setOperator(UserVO operator) {
        this.operator = operator;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "[操作员=" + this.operator + "# 行为=" + this.action +"# 时间=" + this.time +"]";
    }

}

