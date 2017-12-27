package main.java.vo.log;

import main.java.po.log.LogQueryPO;

import java.util.Date;

public class LogQueryVO {

    public Date start;//时间起点
    public Date end;//时间终点

    public LogQueryVO(Date start,Date end){
        this.start=start;
        this.end=end;
    }

    /*得到LogQueryPO*/
    public LogQueryPO getLogQueryPO(){
        LogQueryPO logQueryPO = new LogQueryPO(this.start, this.end);
        return logQueryPO;
    }
}
