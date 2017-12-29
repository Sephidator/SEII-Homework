package main.java.vo.initial;

import main.java.po.initial.InitialQueryPO;

public class InitialQueryVO {

    public int year;//查询时间点

    public InitialQueryVO (int year){
        this.year=year;
    }

    /*得到InitialQueryPO*/
    public InitialQueryPO getInitialQueryPO(){
        InitialQueryPO initialQueryPO = new InitialQueryPO(this.year);
        return initialQueryPO;
    }
}
