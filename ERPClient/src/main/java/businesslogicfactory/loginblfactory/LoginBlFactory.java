package main.java.businesslogicfactory.loginblfactory;

import main.java.businesslogic.loginbl.LoginBl;
import main.java.businesslogicservice.loginblservice.LoginBlService;

public class LoginBlFactory {
    public LoginBlService getService(){
        LoginBlService loginBlService=new LoginBl();
        return loginBlService;
    }
}
