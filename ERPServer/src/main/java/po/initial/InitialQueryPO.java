package main.java.po.initial;

import java.io.Serializable;

public class InitialQueryPO implements Serializable {
    public int year;//时间起点

    public InitialQueryPO(int year) {
        this.year = year;
    }
}
