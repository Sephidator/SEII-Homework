package businesslogicservice.reportblservice;

import vo.report.BusinessConditionQueryVO;

import java.util.ArrayList;

public interface BusinessConditionBlService {
    public ArrayList<Double> getCondition(BusinessConditionQueryVO query);
}
