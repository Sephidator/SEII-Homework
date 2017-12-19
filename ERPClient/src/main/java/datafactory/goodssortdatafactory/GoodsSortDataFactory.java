package main.java.datafactory.goodssortdatafactory;

import main.java.dataservice.goodssortdataservice.GoodsSortDataService;

import java.rmi.Naming;

public class GoodsSortDataFactory {
    public static GoodsSortDataService getService() throws Exception{
        GoodsSortDataService goodsSortDataService=(GoodsSortDataService) Naming.lookup("rmi://127.0.0.1:7200/GoodsSortDataService");
        return goodsSortDataService;
    }
}
