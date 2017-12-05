package main.java.po.initial;

import java.io.Serializable;

public class InitialQueryPO implements Serializable {
    public int start;//时间起点
    public int end;//时间终点

    public InitialQueryPO(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
