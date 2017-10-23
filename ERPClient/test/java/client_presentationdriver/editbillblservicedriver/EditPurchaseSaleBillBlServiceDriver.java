package client_presentationdriver.editbillblservicedriver;

import businesslogicservice.editbillblservice.EditPurchaseSaleBillBlService;
import client_blservicestub.editbillblservicestub.EditPurchaseSaleBillBlServiceStub;
import dataservice.purchasedataservice.PurchaseTradeBillDataService;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditPurchaseSaleBillBlServiceDriver {
    EditPurchaseSaleBillBlService service=new EditPurchaseSaleBillBlServiceStub();

    @Test
    public void getPurchaseBill() throws Exception {
        assertEquals(2,service.getPurchaseBill(0).size());
    }

    @Test
    public void getSaleBill() throws Exception {
        assertEquals(2,service.getSaleBill(0).size());
    }

}