package main.java.businesslogicfactory.saleblfactory;

import main.java.businesslogic.salebl.SaleRefundBillBl;
import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;

public class SaleRefundBillBlFactory {
    public SaleRefundBillBlService getSerivce(){
        SaleRefundBillBlService saleRefundBillBlService=new SaleRefundBillBl();
        return saleRefundBillBlService;
    }
}