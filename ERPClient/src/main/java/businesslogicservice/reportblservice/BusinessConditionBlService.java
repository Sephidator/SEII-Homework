package main.java.businesslogicservice.reportblservice;

import main.java.vo.report.BusinessConditionQueryVO;

import java.util.ArrayList;

public interface BusinessConditionBlService {
    ArrayList<Double> getCondition(BusinessConditionQueryVO query) throws Exception;
}
