package vo;

import java.io.Serializable;
import java.util.Date;

public class BillVO {
    public String ID; //单据编号
    public int state; //单据状态
    public Date time; //创建时间

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "[单据编号=" + this.ID + ", 单据状态=" + this.state +", 创建时间=" + this.time +"]";
    }
}
