package po;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.io.Serializable;
import java.util.Date;

//促销策略对象
public class PromotionPO implements Serializable {
    //促销策略编号
    protected String ID;
    //促销策略类型
    protected int type;
    //促销策略起始时间
    protected Date start;
    //促销策略截止时间
    protected Date end;

    public String getID() {
        return ID;
    }

    public int getType() {
        return type;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
