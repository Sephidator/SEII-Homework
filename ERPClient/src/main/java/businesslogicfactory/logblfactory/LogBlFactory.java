package main.java.businesslogicfactory.logblfactory;

import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogicservice.logblservice.LogBlService;

public class LogBlFactory {
    public LogBlService getService(){
        LogBlService logBlService = new LogBl();
        return logBlService;
    }
}
