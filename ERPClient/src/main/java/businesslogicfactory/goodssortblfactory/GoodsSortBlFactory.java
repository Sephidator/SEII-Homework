package main.java.businesslogicfactory.goodssortblfactory;

import main.java.businesslogic.goodssortbl.GoodsSortBl;
import main.java.businesslogicservice.goodssortblservice.GoodsSortBlService;

public class GoodsSortBlFactory {
    public GoodsSortBlService getService(){
        GoodsSortBlService goodsSortBlService=new GoodsSortBl();
        return goodsSortBlService;
    }
}
