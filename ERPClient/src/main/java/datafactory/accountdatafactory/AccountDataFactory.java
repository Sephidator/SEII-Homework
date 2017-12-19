package main.java.datafactory.accountdatafactory;

import main.java.dataservice.accountdataservice.AccountDataService;

import java.rmi.Naming;

public class AccountDataFactory {
    static public AccountDataService getService() throws Exception{
        AccountDataService service = (AccountDataService) Naming.lookup("rmi://127.0.0.1:7200/AccountDataService");
        return service;
    }
    /*
    * 给方法加上static，我之前忘加了
    * 调用的时候，写法是：
    * AccountDataService service=AccountDataFactory.getService();
    * */
}
