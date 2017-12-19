package main.java.datafactory.saledatafactory;

import main.java.dataservice.saledataservice.SaleRefundBillDataService;

import java.rmi.Naming;

public class SaleRefundBillDataFactory {
    public static SaleRefundBillDataService getService() throws Exception{
        SaleRefundBillDataService saleRefundBillDataService=(SaleRefundBillDataService) Naming.lookup("rmi://127.0.0.1:7200/SaleRefundBillDataService");
        return saleRefundBillDataService;
    }
}
