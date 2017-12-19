package main.java.businesslogicfactory.initialblfactory;

import main.java.businesslogic.initialbl.InitialBl;
import main.java.businesslogicservice.initialblservice.InitialBlService;

public class InitialBlFactory {
    public static InitialBlService getService(){
        InitialBlService initialBlService = new InitialBl();
        return initialBlService;
    }
}
