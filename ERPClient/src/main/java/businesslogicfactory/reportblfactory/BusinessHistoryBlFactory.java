package main.java.businesslogicfactory.reportblfactory;

import main.java.businesslogic.reportbl.BusinessHistoryBl;
import main.java.businesslogicservice.reportblservice.BusinessHistoryBlService;

public class BusinessHistoryBlFactory {
    public static BusinessHistoryBlService getService(){
        BusinessHistoryBlService businessHistoryBlService = new BusinessHistoryBl();
        return businessHistoryBlService;
    }
}
