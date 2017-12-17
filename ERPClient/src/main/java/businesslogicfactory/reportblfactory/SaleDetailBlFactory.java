package main.java.businesslogicfactory.reportblfactory;

import main.java.businesslogic.reportbl.SaleDetailBl;
import main.java.businesslogicservice.reportblservice.SaleDetailBlService;

public class SaleDetailBlFactory {
    public SaleDetailBlService getService(){
        SaleDetailBlService saleDetailBlService = new SaleDetailBl();
        return saleDetailBlService;
    }
}
