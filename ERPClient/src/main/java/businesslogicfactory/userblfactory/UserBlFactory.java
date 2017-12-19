package main.java.businesslogicfactory.userblfactory;

import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogicservice.userblservice.UserBlService;

public class UserBlFactory {
    public static UserBlService getService(){
        UserBlService userBlService = new UserBl();
        return userBlService;
    }
}
