package server_bldriver.editbilldataservicedriver;

import dataservice.editbilldataservice.EditInventoryBillDataService;
import org.junit.Test;
import server_dataservicestub.editbilldataservicestub.EditInventoryBillDataServiceStub;

import static org.junit.Assert.assertEquals;

public class EditInventoryBillDataServiceDriver {
    EditInventoryBillDataService service=new EditInventoryBillDataServiceStub();

    @Test
    public void getInventoryBill() throws Exception {
        assertEquals(2,service.getInventoryBill(0).size());
    }

}