package main.java.datafactory.goodsdatafactory;

import main.java.dataservice.goodsdataservice.GoodsDataService;

import java.rmi.Naming;

public class GoodsDataFactory {
    public GoodsDataService getService() throws Exception{
        GoodsDataService goodsDataService=(GoodsDataService) Naming.lookup("rmi://127.0.0.1:7200/GoodsDataService");
        return goodsDataService;
    }
}
