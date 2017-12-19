package main.java.datafactory.promotiondatafactory;

import main.java.dataservice.promotiondataservice.PromotionDataService;

import java.rmi.Naming;

public class PromotionDataFactory {
    public static PromotionDataService getService() throws Exception {
        PromotionDataService service = (PromotionDataService) Naming.lookup("rmi://127.0.0.1:7200/PromotionDataService");
        return service;
    }
}
