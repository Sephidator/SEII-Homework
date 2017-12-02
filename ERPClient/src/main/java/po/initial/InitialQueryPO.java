package main.java.po.initial;

import main.java.po.QueryPO;

public class InitialQueryPO extends QueryPO {
    public int start;//时间起点
    public int end;//时间终点

    public InitialQueryPO(int start, int end) {
        this.ID = ID;
        this.start = start;
        this.end = end;
    }
}
