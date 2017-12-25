package main.java.businesslogicfactory.saleblfactory;

import main.java.businesslogic.salebl.SaleRefundBillBl;
import main.java.businesslogicservice.saleblservice.SaleRefundBillBlService;

public class SaleRefundBillBlFactory {
    public static SaleRefundBillBlService getService(){
        SaleRefundBillBlService saleRefundBillBlService=new SaleRefundBillBl();
        return saleRefundBillBlService;
    }
}
