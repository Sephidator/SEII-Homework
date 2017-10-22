package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/*
 * 操作日志数据
 * @author:
 * @version:
 */

public class LogVO implements Serializable{

    private ArrayList<String> operatorList;//执行动作的操作人

    private ArrayList<String> actionList;//发生的动作

    private ArrayList<Date> timeList;//执行动作的时间

    /*构造函数，添加日志信息*/
    public LogVO(String o, String a, Date t) {
        addOperator(o);
        addAction(a);
        addTime(t);
    }

    public ArrayList<String> getOperatorList() {
        return operatorList;
    }

    public ArrayList<String> getActionList() {
        return actionList;
    }

    public ArrayList<Date> getTimeList() {
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

    /**
     * 以#分开各个ArrayList
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "[操作员=" + this.operatorList + "# 行为=" + this.actionList +"# 时间=" + this.timeList +"]";
    }

}

