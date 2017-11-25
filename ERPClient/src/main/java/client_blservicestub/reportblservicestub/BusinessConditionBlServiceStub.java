package main.java.client_blservicestub.reportblservicestub;

import main.java.businesslogicservice.reportblservice.BusinessConditionBlService;
import main.java.vo.report.BusinessConditionQueryVO;

import java.util.ArrayList;

public class BusinessConditionBlServiceStub implements BusinessConditionBlService {

    @Override
    public ArrayList<Double> getCondition(BusinessConditionQueryVO query) {
        ArrayList<Double> list = new ArrayList<>();
        list.add(12000.0);
        list.add(0.0);
        list.add(1500.0);
        list.add(5000.0);
        list.add(500.0);
        list.add(5000.0);
        return list;
    }
}
