package main.java.businesslogicfactory.saleblfactory;

import main.java.businesslogic.salebl.SaleTradBillBl;
import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;
import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;

public class SaleTradeBillBlFactory {
    public SaleTradeBillBlService getService(){
        SaleTradeBillBlService saleTradeBillBlService=new SaleTradBillBl();
        return saleTradeBillBlService;
    }
}
