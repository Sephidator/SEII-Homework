package vo.bill;

import java.io.Serializable;
import java.util.Date;

public class BillVO {
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

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "[单据编号=" + this.ID + ", 单据状态=" + this.state +", 创建时间=" + this.time +"]";
    }
}
