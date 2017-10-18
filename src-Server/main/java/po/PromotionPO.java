package po;

import java.util.Date;

//促销策略对象
public class PromotionPO {
    //促销策略ID
    private int ID;
    //促销策略起始时间
    private Date start;
    //促销策略截止时间
    private Date end;

    public PromotionPO(int id, Date start, Date end) {
        this.ID = ID;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return ID;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
