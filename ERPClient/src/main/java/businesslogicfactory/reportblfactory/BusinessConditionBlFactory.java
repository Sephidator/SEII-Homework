package main.java.businesslogicfactory.reportblfactory;

import main.java.businesslogic.reportbl.BusinessConditionBl;
import main.java.businesslogicservice.reportblservice.BusinessConditionBlService;

public class BusinessConditionBlFactory {
    public BusinessConditionBlService getService(){
        BusinessConditionBlService businessConditionBlService = new BusinessConditionBl();
        return businessConditionBlService;
    }
}
