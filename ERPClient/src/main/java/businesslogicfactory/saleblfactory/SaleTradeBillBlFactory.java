package main.java.businesslogicfactory.saleblfactory;

import main.java.businesslogic.salebl.SaleTradeBillBl;
import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;

public class SaleTradeBillBlFactory {
    public static SaleTradeBillBlService getService(){
        SaleTradeBillBlService saleTradeBillBlService=new SaleTradeBillBl();
        return saleTradeBillBlService;
    }
}
