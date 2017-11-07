package businesslogic.reportbl;

import businesslogicservice.reportblservice.BusinessConditionBlService;
import vo.report.BusinessConditionQueryVO;

import java.util.ArrayList;

public class BusinessConditionBl implements BusinessConditionBlService {
    @Override
    public ArrayList<Double> getCondition(BusinessConditionQueryVO query) {
        return null;
    }
}
