package main.java.businesslogicfactory.goodsblfactory;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogicservice.goodsblservice.GoodsBlService;

public class GoodsBlFactory {

    public static GoodsBlService getService(){
        GoodsBlService goodsBlService=new GoodsBl();
        return goodsBlService;
    }
}
