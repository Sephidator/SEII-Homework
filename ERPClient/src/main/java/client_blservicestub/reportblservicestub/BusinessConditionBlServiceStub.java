package main.java.client_blservicestub.reportblservicestub;

import main.java.businesslogicservice.reportblservice.BusinessConditionBlService;
import main.java.vo.report.BusinessConditionQueryVO;

import java.util.ArrayList;

public class BusinessConditionBlServiceStub implements BusinessConditionBlService {

    @Override
    public ArrayList<Double> getCondition(BusinessConditionQueryVO query) throws Exception {
        return new ArrayList<>();
    }
}
