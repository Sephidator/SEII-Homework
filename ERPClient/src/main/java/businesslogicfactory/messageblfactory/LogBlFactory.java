package main.java.businesslogicfactory.messageblfactory;

import main.java.businesslogic.logbl.LogBl;
import main.java.businesslogicservice.logblservice.LogBlService;

public class LogBlFactory {
    public LogBlService getService(){
        LogBlService logBlService = new LogBl();
        return logBlService;
    }
}
