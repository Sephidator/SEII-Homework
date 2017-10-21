package po;

import java.io.Serializable;
import java.util.Date;

public class BillPO implements Serializable {
    protected String ID; //单据编号
    protected int state; //单据状态
    protected Date time; //创建时间

    public String getID() {
        return ID;
    }

    public int getState() {
        return state;
    }

    public Date getTime() {
        return time;
    }
}
