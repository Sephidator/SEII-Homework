package main.java.businesslogicfactory.accountblfactory;

import main.java.businesslogic.accountbl.AccountBl;
import main.java.businesslogicservice.accountblservice.AccountBlService;

public class AccountBlFactory {
    static public AccountBlService getService(){
        AccountBlService accountBlService=new AccountBl();
        return accountBlService;
    }
    /*
    * 给方法加上static，我之前忘加了
    * 调用的时候，写法是：
    * AccountBlService service=AccountBlFactory.getService();
    * */
}
