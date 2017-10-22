package vo;

import java.io.Serializable;
import java.util.Date;

public class BillVO implements Serializable {
    protected String ID; //单据编号
    protected int state; //单据状态
    protected Date time; //创建时间

    public String getID(){return this.ID;}

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[单据编号=" + this.ID + ", 单据状态=" + this.state +", 创建时间=" + this.time +"]";
    }
}
