package main.java.businesslogicfactory.promotionblfactory;

import main.java.businesslogic.promotionbl.PromotionBl;
import main.java.businesslogicservice.promotionblservice.PromotionBlService;

public class PromotionBlFactory {
    public static PromotionBlService getService(){
        PromotionBlService promotionBlService = new PromotionBl();
        return promotionBlService;
    }
}
