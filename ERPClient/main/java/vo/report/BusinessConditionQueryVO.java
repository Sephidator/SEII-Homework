package vo.report;

import businesslogicservice.reportblservice.BusinessConditionBlService;

import java.util.Date;

public class BusinessConditionQueryVO {
    public Date start;
    public Date end;

    public BusinessConditionQueryVO(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public BusinessConditionQueryVO() {
    }
}
