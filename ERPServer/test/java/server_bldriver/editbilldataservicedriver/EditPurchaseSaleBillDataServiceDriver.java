package server_bldriver.editbilldataservicedriver;

import dataservice.editbilldataservice.EditPurchaseSaleBillDataService;
import org.junit.Test;
import server_dataservicestub.editbilldataservicestub.EditPurchaseSaleBillDataServiceStub;

import static org.junit.Assert.assertEquals;

public class EditPurchaseSaleBillDataServiceDriver {
    EditPurchaseSaleBillDataService service=new EditPurchaseSaleBillDataServiceStub();

    @Test
    public void getPurchaseBill() throws Exception {
        assertEquals(2,service.getPurchaseBill(0).size());
    }

    @Test
    public void getSaleBill() throws Exception {
        assertEquals(2,service.getSaleBill(0).size());
    }

}