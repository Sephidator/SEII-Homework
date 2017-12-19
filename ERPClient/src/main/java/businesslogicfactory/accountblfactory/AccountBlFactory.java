package main.java.businesslogicfactory.accountblfactory;

import main.java.businesslogic.accountbl.AccountBl;
import main.java.businesslogicservice.accountblservice.AccountBlService;

public class AccountBlFactory {
    public static AccountBlService getService(){
        AccountBlService accountBlService=new AccountBl();
        return accountBlService;
    }
}
