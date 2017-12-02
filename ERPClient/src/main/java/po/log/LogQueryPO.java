package main.java.po.log;

import java.io.Serializable;
import java.util.Date;

public class LogQueryPO implements Serializable {
    public Date start;//起始时间
    public Date end;//终点时间

    public LogQueryPO(Date start, Date end) {
        this.start = start;
        this.end = end;
    }
}
