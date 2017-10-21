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

    private ArrayList<String> operatorList;//执行动作的操作人

    private ArrayList<String> actionList;//发生的动作

    private ArrayList<Date> timeList;//执行动作的时间

    /*构造函数，添加日志信息*/
    public LogPO(String o, String a, Date t) {
        addOperator(o);
        addAction(a);
        addTime(t);
    }

    /*返回日志中操作人员*/
    public ArrayList<String> getOperator() {
        return operatorList;
    }

    /*返回日志中动作*/
    public ArrayList<String> getAction() {
        return actionList;
    }

    /*返回日志中时间*/
    public ArrayList<Date> getTime() {
        return timeList;
    }

    /********************添加日志信息***********************/

    /*添加操作人员，成功为true*/
    public boolean addOperator(String operator) {
        operatorList.add(operator);
        return true;
    }

    /*添加动作，成功为true*/
    public boolean addAction(String action) {
        actionList.add(action);
        return true;
    }

    /*添加时间，成功为true*/
    public boolean addTime(Date time) {
        timeList.add(time);
        return true;
    }

}

