package main.java.runner;

import main.java.data.DataHelper;
import main.java.data.promotiondata.PromotionData;
import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.po.promotion.PromotionTotalPO;

import java.util.ArrayList;
import java.util.Date;

public class ServerRunner {

    public static void main(String[] args) throws Exception {
        new DataHelper();
        new RemoteHelper();
        System.out.println("Server Start");
        PromotionDataService service = new PromotionData();
        System.out.println(service.insert(new PromotionTotalPO("hh", new Date(), new Date(), 1000, 50, new ArrayList<>())));
    }
}
