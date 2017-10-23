package client_presentationdriver.editbillblservicedriver;

import businesslogicservice.editbillblservice.EditInventoryBillBlService;
import client_blservicestub.editbillblservicestub.EditInventoryBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EditInventoryBillBlServiceDriver {
    EditInventoryBillBlService service=new EditInventoryBillBlServiceStub();
    
    @Test
    public void getPurchaseBill() throws Exception {
        assertEquals(2,service.getInventoryBill(0).size());
    }
    
}