package main.java.vo.log;

import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.log.LogPO;
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
        this.operator = new UserVO();
        this.action = "";
        this.time = new Date();
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

    /*得到LogPO*/
    public LogPO getLogPO()throws Exception{
        LogPO logPO = new LogPO();
        logPO.setOperatorID(this.getOperator().getID());
        logPO.setAction(this.getAction());
        logPO.setTime(this.getTime());

        return logPO;
    }

    /*得到LogVO*/
    public LogVO(LogPO logPO)throws Exception{
        UserTool userTool = new UserBl();
        this.setOperator(userTool.find(logPO.getOperatorID()));
        this.setTime(logPO.getTime());
        this.setAction(logPO.getAction());
    }
}

