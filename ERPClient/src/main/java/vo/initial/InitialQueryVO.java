package main.java.vo.initial;

import main.java.po.initial.InitialQueryPO;

public class InitialQueryVO {

    public int start;//时间起点

    public int end;//时间终点

    /*得到InitialQueryPO*/
    public InitialQueryPO getInitialQueryPO(){
        InitialQueryPO initialQueryPO = new InitialQueryPO(this.start, this.end);
        return initialQueryPO;
    }
}
