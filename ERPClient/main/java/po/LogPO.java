package po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/*
 * 操作日志数据
 * @author:
 * @version:
 */

public class LogPO implements Serializable{

    private String operator;//执行动作的操作人

    private String action;//发生的动作

    private Date time;//执行动作的时间

    public LogPO(String operator, String action, Date time) {
        this.operator = operator;
        this.action = action;
        this.time = time;
    }

    public String getOperator() {
        return operator;
    }

    public String getAction() {
        return action;
    }

    public Date getTime() {
        return time;
    }
}

