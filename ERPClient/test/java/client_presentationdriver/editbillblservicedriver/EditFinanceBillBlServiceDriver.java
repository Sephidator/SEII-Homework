package client_presentationdriver.editbillblservicedriver;

import businesslogicservice.editbillblservice.EditFinanceBillBlService;
import client_blservicestub.editbillblservicestub.EditFinanceBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EditFinanceBillBlServiceDriver {
    EditFinanceBillBlService service=new EditFinanceBillBlServiceStub();
    
    @Test
    public void getPurchaseBill() throws Exception {
        assertEquals(2,service.getFinanceBill(0).size());
    }
    
}